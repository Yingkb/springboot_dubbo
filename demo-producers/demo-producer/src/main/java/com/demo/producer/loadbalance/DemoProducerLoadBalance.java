package com.demo.producer.loadbalance;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.LoadBalance;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Yingkb
 * @version 1.0.0
 * @ClassName DemoProducerLoadBalance.java
 * @Description TODO
 * @createTime 2020-11-02 16:23
 */
public class DemoProducerLoadBalance implements LoadBalance {
    private static final String NAME = "PRO";

    @Override
    public <T> Invoker<T> select(List<Invoker<T>> list, URL url, Invocation invocation) throws RpcException {
        List<Invoker<T>> lists = new ArrayList<>();
        for (Invoker<T> t : list) {
            lists.add(t);
        }
        //获取消费端参数
        String system = invocation.getAttachment("system");
        System.out.println("====>>>>>>负载均衡:" + system);
        Iterator<Invoker<T>> iterator = lists.iterator();
        while (iterator.hasNext()) {
            Invoker<T> next = iterator.next();
            String producerSystem = next.getUrl().getParameter("system", "");
            if (Objects.equals(system, producerSystem)) {
                return next;
            } else if(Objects.equals(NAME,producerSystem)){
                iterator.remove();
            }
        }
        return this.randomSelect(lists, url, invocation);
    }

    static int calculateWarmupWeight(int uptime, int warmup, int weight) {
        int ww = (int) ((float) uptime / ((float) warmup / (float) weight));
        return ww < 1 ? 1 : (ww > weight ? weight : ww);
    }

    protected int getWeight(Invoker<?> invoker, Invocation invocation) {
        int weight = invoker.getUrl().getMethodParameter(invocation.getMethodName(), "weight", 100);
        if (weight > 0) {
            long timestamp = invoker.getUrl().getParameter("remote.timestamp", 0L);
            if (timestamp > 0L) {
                int uptime = (int) (System.currentTimeMillis() - timestamp);
                int warmup = invoker.getUrl().getParameter("warmup", 600000);
                if (uptime > 0 && uptime < warmup) {
                    weight = calculateWarmupWeight(uptime, warmup, weight);
                }
            }
        }

        return weight >= 0 ? weight : 0;
    }

    /**
     * 重写了一遍随机负载策略
     *
     * @param <T>
     * @param invokers
     * @param url
     * @param invocation
     * @return
     */
    private <T> Invoker randomSelect(List<Invoker<T>> invokers, URL url, Invocation invocation) {
        int length = invokers.size();
        boolean sameWeight = true;
        int[] weights = new int[length];
        int firstWeight = this.getWeight(invokers.get(0), invocation);
        weights[0] = firstWeight;
        int totalWeight = firstWeight;
        int offset;
        int i;
        for (offset = 1; offset < length; ++offset) {
            i = this.getWeight(invokers.get(offset), invocation);
            weights[offset] = i;
            totalWeight += i;
            if (sameWeight && i != firstWeight) {
                sameWeight = false;
            }
        }
        if (totalWeight > 0 && !sameWeight) {
            offset = ThreadLocalRandom.current().nextInt(totalWeight);
            for (i = 0; i < length; ++i) {
                offset -= weights[i];
                if (offset < 0) {
                    return invokers.get(i);
                }
            }
        }
        return invokers.get(ThreadLocalRandom.current().nextInt(length));
    }
}

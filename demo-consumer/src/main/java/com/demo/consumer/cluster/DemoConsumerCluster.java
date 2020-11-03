package com.demo.consumer.cluster;

import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.Cluster;
import org.apache.dubbo.rpc.cluster.Directory;
import org.apache.dubbo.rpc.cluster.LoadBalance;
import org.apache.dubbo.rpc.cluster.support.AbstractClusterInvoker;

import java.util.List;

/**
 * @author Yingkb
 * @version 1.0.0
 * @ClassName DemoConsumerCluster.java
 * @Description TODO
 * @createTime 2020-11-03 17:46
 */
public class DemoConsumerCluster implements Cluster {
    @Override
    public <T> Invoker<T> join(Directory<T> directory) throws RpcException {
        return new AbstractClusterInvoker<T>(directory) {
            @Override
            protected Result doInvoke(Invocation invocation, List<Invoker<T>> list, LoadBalance loadBalance) throws RpcException {

                return null;
            }
        };
    }
}

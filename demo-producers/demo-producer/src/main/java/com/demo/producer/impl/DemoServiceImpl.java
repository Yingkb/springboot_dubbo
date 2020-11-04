package com.demo.producer.impl;

import com.demo.api.DemoService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author Yingkb
 * @version 1.0.0
 * @ClassName DemoServiceImpl.java
 * @Description TODO
 * @createTime 2020-11-03 15:47
 */
@Service(version = "1.0.0",loadbalance = "demoTest")
public class DemoServiceImpl implements DemoService {

    @Value("${server.port}")
    private String port;

    @Override
    public String getNotifyUrl() {
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "====>>>>>调用服务端口："+port;
    }
}

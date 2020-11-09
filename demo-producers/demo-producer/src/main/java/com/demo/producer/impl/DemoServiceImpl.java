package com.demo.producer.impl;

import com.demo.api.DemoService;
import com.demo.api.dto.Person;
import com.demo.api.dto.ValidationParameter;
import org.apache.dubbo.common.utils.PojoUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public String getNotifyUrl(ValidationParameter validationParameter) {
        //        try {
//            Thread.sleep(7000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "====>>>>>调用服务端口："+port;
    }

    @Override
    public String getApplicationName() {
        return "====>>>>调用服务名："+applicationName;
    }

    @Override
    public String getName(String name) {
        return "===>>>producer"+name;
    }

    @Override
    public List<Person> getPersonList() {
        Person person = new Person();
        person.setName("张三");
        person.setAge(11);
        return Collections.singletonList(person);

    }

    @Override
    public List<Person> getPersonParamter(List<Person> personList) {
        return personList;
    }
}

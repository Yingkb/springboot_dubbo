package com.demo.api;

import com.demo.api.dto.Person;
import com.demo.api.dto.ValidationParameter;

import java.util.List;

/**
 * @author Yingkb
 * @version 1.0.0
 * @ClassName DemoService.java
 * @Description TODO
 * @createTime 2020-11-02 14:35
 */
public interface DemoService {

    @interface GetNotifyUrl{}
    String getNotifyUrl(ValidationParameter validationParameter);

    String getApplicationName();


    String getName(String name);

    List<Person> getPersonList();

    List<Person> getPersonParamter(List<Person> personList);

}

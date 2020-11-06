package com.demo.api;

import com.demo.api.dto.ValidationParameter;

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

}

package com.demo.api.dto;

import com.demo.api.DemoService;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Yingkb
 * @version 1.0.0
 * @ClassName ValidationParameter.java
 * @Description TODO
 * @createTime 2020-11-06 09:55
 */

public class ValidationParameter implements Serializable {
    private static final long serialVersionUID = -1800881000075081104L;

    @NotNull(message = "id不能为空",groups = DemoService.GetNotifyUrl.class)
//    @NotNull(message = "id不能为空")
    private Integer id;

    @NotBlank(message = "姓名不能空")
    private String name;

    private Integer age;

    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

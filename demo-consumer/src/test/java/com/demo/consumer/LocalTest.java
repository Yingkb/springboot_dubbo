package com.demo.consumer;

import org.apache.dubbo.common.utils.PojoUtils;
import org.junit.jupiter.api.Test;

/**
 * @author Yingkb
 * @version 1.0.0
 * @ClassName LocalTest.java
 * @Description TODO
 * @createTime 2020-11-09 11:08
 */
public class LocalTest {


    @Test
    public void PojoTest(){
        Student student = new Student();
        student.setAge(10);
        student.setName("小张");

        Object generalize = PojoUtils.generalize(student);
        System.out.println(generalize);
    }
}

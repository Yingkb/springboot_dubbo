package com.demo.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Yingkb
 * @version 1.0.0
 * @ClassName WebProducerAppliaction.java
 * @Description TODO
 * @createTime 2020-11-03 15:46
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableAsync
public class WebProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebProducerApplication.class, args);
    }
}

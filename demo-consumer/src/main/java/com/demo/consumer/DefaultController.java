package com.demo.consumer;

import com.demo.api.DemoService;
import com.demo.api.dto.ValidationParameter;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yingkb
 * @version 1.0.0
 * @ClassName DefaultController.java
 * @Description TODO
 * @createTime 2020-11-03 16:02
 */
@RestController("/")
public class DefaultController {
    @Reference(check = false, version = "1.0.0", cluster = "failback", validation = "true")
    DemoService demoService;

    @RequestMapping("getPort")
    public String getPort(String str) throws Exception {
        //调用成功==>>>>>>>>:访问端口:9189
//        RpcContext.getContext().setAttachment("system","PRE");
        RpcContext.getContext().setAttachment("system", str);
        ValidationParameter validationParameter = new ValidationParameter();
        return "结果==>>" + demoService.getNotifyUrl(validationParameter);
    }
}

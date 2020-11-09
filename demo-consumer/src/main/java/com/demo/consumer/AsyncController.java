package com.demo.consumer;

import com.demo.api.DemoService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Yingkb
 * @version 1.0.0
 * @ClassName AsyncController.java
 * @Description TODO
 * @createTime 2020-11-09 18:08
 */
@RestController("/a")
public class AsyncController {

    @Reference(
            check = false, version = "1.0.0",
            cluster = "failback", validation = "true",
            generic = true,
            async = true)
    DemoService demoService;

    @RequestMapping("async")
    public String async(String name) throws ExecutionException, InterruptedException {
         RpcContext.getContext().asyncCall(()->{
            demoService.getName(name);
        });
        Object o = RpcContext.getContext().getCompletableFuture().get();
        System.out.println("====>>>"+o.toString());
        return o.toString();
    }

}

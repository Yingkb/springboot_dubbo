package com.demo.consumer;

import com.demo.api.DemoService;
import com.demo.api.dto.Person;
import com.demo.api.dto.ValidationParameter;
import com.google.gson.JsonArray;
import org.apache.dubbo.common.utils.PojoUtils;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.service.GenericService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.Servlet;
import java.util.Collections;
import java.util.List;

/**
 * @author Yingkb
 * @version 1.0.0
 * @ClassName DefaultController.java
 * @Description TODO
 * @createTime 2020-11-03 16:02
 */
@RestController("/")
public class DefaultController {
    @Reference(
            check = false, version = "1.0.0",
            cluster = "failback", validation = "true",
            generic = true,
            async = true)
    DemoService demoService;

    @RequestMapping("getPort")
    public String getPort(String str) throws Exception {
        //调用成功==>>>>>>>>:访问端口:9189
//        RpcContext.getContext().setAttachment("system","PRE");
        RpcContext.getContext().setAttachment("system", str);
        ValidationParameter validationParameter = new ValidationParameter();
        return "结果==>>" + demoService.getNotifyUrl(validationParameter);
    }

    //-------------------------- 泛化支持 -------------------------- //
    @RequestMapping("getStr")
    public String getStr(String str){
        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setInterface("com.demo.api.DemoService");
        reference.setVersion("1.0.0");
        reference.setGeneric(true);
        GenericService genericService  = reference.get();
        Object data = genericService.$invoke("getName", new String[] { "java.lang.String" },
                new Object[] { str});
       return data.toString();
    }

    @RequestMapping("getList")
    public String getList(){
        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setInterface("com.demo.api.DemoService");
        reference.setVersion("1.0.0");
        reference.setGeneric(true);

        GenericService genericService  = reference.get();
//        Object data = genericService.$invoke("getPersonList", new String[] {},
//                new Object[]{});
        //List入参
        Person person = new Person();
        person.setAge(1);
        person.setName("张三");
        List<Person> persons = Collections.singletonList(person);
        Object generalize = PojoUtils.generalize(persons);
        Object data = genericService.$invoke("getPersonParamter", new String[] {"java.util.List"},
                new Object[]{generalize});
        return data.toString();
    }
}

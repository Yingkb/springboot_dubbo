package com.demo.consumer.gloab;

import com.demo.consumer.utils.DateConverter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Yingkb
 * @version 1.0.0
 * @ClassName GloadException.java
 * @Description 全局异常处理
 * @createTime 2020-11-06 11:29
 */
@ControllerAdvice
public class GloabExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String handle(Exception e){
         List<String> violationMsgList;
        if(e instanceof ConstraintViolationException){
            ConstraintViolationException cve = (ConstraintViolationException)e;
            Set<ConstraintViolation<?>> constraintViolations = cve.getConstraintViolations();
            violationMsgList = new ArrayList<>();
            for(ConstraintViolation c:constraintViolations){
                violationMsgList.add(c.getMessage());
            }
            if(!CollectionUtils.isEmpty(violationMsgList)){
                return violationMsgList.toString();
            }
        }
        return e.getMessage();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        GenericConversionService genericConversionService = (GenericConversionService) binder.getConversionService();
        if (genericConversionService != null) {
            genericConversionService.addConverter(new DateConverter());
        }
    }


}

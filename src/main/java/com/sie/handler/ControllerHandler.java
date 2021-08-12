package com.sie.handler;

import com.sie.common.Result;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ControllerHandler
 * @Description TODO 异常处理
 * @Author 徐啸儒
 * @Data 2021/7/29 15:12
 * @Version 1.0
 **/
@ControllerAdvice
public class ControllerHandler {

    /*
     * @Author 徐啸儒
     * @Description //TODO 全局异常
     * @Date
     * @Param [e]
     * @return com.sie.common.Result
    **/
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handleException(Exception e){
        e.printStackTrace();
        return Result.error("500","执行全局异常处理");
    }


    /*
     * @Author 徐啸儒
     * @Description //TODO AuthorizationException
     * @Date
     * @Param [e]
     * @return com.sie.common.Result
    **/
    @ExceptionHandler(value= AuthorizationException.class)
    @ResponseBody
    public Result handleException(AuthorizationException e){
        //e.printStackTrace();
        //获取错误中中括号的内容
        String message = e.getMessage();
        String msg=message.substring(message.indexOf("[")+1,message.indexOf("]"));
        //System.out.println("message: "+message);
        //判断是角色错误还是权限错误
        if (message.contains("role")) {
           return  Result.error("400", "对不起，您没有" + msg + "角色");
        } else if (message.contains("permission")) {
           return Result.error("400", "对不起，您没有" + msg + "权限");
        } else {
           return Result.error("400", "对不起，您的权限有误");
        }

    }



}

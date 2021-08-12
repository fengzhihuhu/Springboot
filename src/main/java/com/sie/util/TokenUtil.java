package com.sie.util;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;

/**
 * @ClassName TokenUtil
 * @Description TODO 从头信息获取token
 * @Author 徐啸儒
 * @Data 2021/8/5 14:21
 * @Version 1.0
 **/
public class TokenUtil {

    //获取请求的token
    public static String getRequestToken(HttpServletRequest httpServletRequest){
       //从header中获取token
        String token = httpServletRequest.getHeader("token");
        //System.out.println("token:"+token);

        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isBlank(token)){
            token=httpServletRequest.getParameter("token");
        }
        return token;
    }

}

package com.sie.shiro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sie.util.HttpContextUtil;
import com.sie.util.TokenUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName AuthFilter
 * @Description TODO 过滤token
 * @Author 徐啸儒
 * @Data 2021/8/5 14:17
 * @Version 1.0
 **/
@Component
public class AuthFilter extends AuthenticatingFilter {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    //生成自定义token
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        //获取请求token
        String token = TokenUtil.getRequestToken((HttpServletRequest) servletRequest);
        return new AuthToken(token);
    }

    //所有请求
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (((HttpServletRequest) request).getMethod().equals(RequestMethod.OPTIONS.name())){
            return true;
        }
        return false;
    }

    //访问的请求,会调用onAccessDenied方法，onAccessDenied方法先获取 token，再调用executeLogin方法
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        //获取请求token，如果token不存在，直接返回
        String token = TokenUtil.getRequestToken((HttpServletRequest) servletRequest);
        if (StringUtils.isBlank(token)){
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtil.getOrigin());
            httpResponse.setCharacterEncoding("UTF-8");
            Map<String, Object> result = new HashMap<>();
            result.put("status",400);
            result.put("msg","请先登录");
            String json = MAPPER.writeValueAsString(token);
            httpResponse.getWriter().print(json);
            return false;
        }
        return executeLogin(servletRequest,servletResponse);
    }

    //token失效的时候调用
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtil.getOrigin());
        httpResponse.setCharacterEncoding("UTF-8");
        try {
            //处理登录失败的异常
            Throwable throwable = e.getCause() == null ? e : e.getCause();
            Map<String, Object> result = new HashMap<>();
            result.put("status", 400);
            result.put("msg", "登录凭证已失效，请重新登录");
            String json = MAPPER.writeValueAsString(result);
            httpResponse.getWriter().print(json);
        }catch (IOException e1){ }
        return false;
    }
}

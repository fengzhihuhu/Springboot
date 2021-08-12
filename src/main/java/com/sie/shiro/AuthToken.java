package com.sie.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @ClassName AuthToken
 * @Description TODO shiro自定义Token类
 * @Author 徐啸儒
 * @Data 2021/8/5 14:12
 * @Version 1.0
 **/
public class AuthToken extends UsernamePasswordToken {

    private String token;

    public AuthToken(String token){
        this.token=token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}

package com.sie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * @Author 徐啸儒
 * @Description //TODO 跨域处理
 * @Date
 * @Param
 * @return
**/
@Configuration
public class CorsConfig implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry) {

        // 设置允许跨域的路由
        registry.addMapping("/**")
                // 设置允许跨域请求的域名
                .allowedOriginPatterns("*")
                // 是否允许证书（cookies）
                .allowCredentials(true)
                // 设置允许的方法
                .allowedMethods("*")
                .allowedHeaders(CorsConfiguration.ALL)
                .allowedMethods(CorsConfiguration.ALL)
                // 跨域允许时间
                .maxAge(3600);
    }
}
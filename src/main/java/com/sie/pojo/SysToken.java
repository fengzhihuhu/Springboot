package com.sie.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName SysToken
 * @Description TODO token
 * @Author 徐啸儒
 * @Data 2021/8/9 8:37
 * @Version 1.0
 **/
@Data
public class SysToken {

    private Integer userId;
    private String token;
    private Date expireTime;
    private Date updateTime;
}

package com.sie.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.Set;

/**
 * @ClassName User
 * @Description TODO
 * @Author 徐啸儒
 * @Data 2021/8/6 10:01
 * @Version 1.0
 **/
@Data
public class User {

    private Integer id;

    private String username;

    private String password;

    private String nickname;

    private String email;

    private String phone;

    private String sex;

    private String description;

    private Integer status;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    private Integer deleteFlag;

}

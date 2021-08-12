package com.sie.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName LoginDTO
 * @Description TODO 登录测试类
 * @Author 徐啸儒
 * @Data 2021/8/6 16:04
 * @Version 1.0
 **/
@Data
public class LoginDTO {

    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;


}

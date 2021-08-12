package com.sie.DTO;

import com.sie.pojo.Role;
import lombok.Data;

import java.util.Set;

/**
 * @ClassName UserVO
 * @Description TODO
 * @Author 徐啸儒
 * @Data 2021/8/9 9:30
 * @Version 1.0
 **/
@Data
public class UserVO {

    private Integer id;
    private String username;
    private Set<RoleVO> roles;
}

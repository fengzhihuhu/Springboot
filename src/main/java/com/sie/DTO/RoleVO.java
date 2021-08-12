package com.sie.DTO;

import com.sie.pojo.Permission;
import lombok.Data;

import java.util.Set;

/**
 * @ClassName RoleVO
 * @Description TODO
 * @Author 徐啸儒
 * @Data 2021/8/9 9:33
 * @Version 1.0
 **/
@Data
public class RoleVO {

    private Integer id;
    private String name;
    private Set<Permission> permissions;
}

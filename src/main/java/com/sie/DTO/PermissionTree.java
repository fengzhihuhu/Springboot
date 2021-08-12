package com.sie.DTO;

import com.sie.pojo.Role;
import lombok.Data;

import java.util.List;

/**
 * @ClassName RoleTree
 * @Description TODO
 * @Author 徐啸儒
 * @Data 2021/8/10 13:50
 * @Version 1.0
 **/
@Data
public class PermissionTree  {

    private String roleId;
    private List<String> authIds;

}

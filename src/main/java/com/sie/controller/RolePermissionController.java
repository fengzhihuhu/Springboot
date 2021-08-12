package com.sie.controller;

import com.sie.DTO.PermissionIds;
import com.sie.DTO.PermissionTree;
import com.sie.DTO.PermissionVO;
import com.sie.common.Result;
import com.sie.mapper.PermissionMapper;
import com.sie.mapper.RolePermissionMapper;
import com.sie.pojo.RolePermissionKey;
import com.sie.service.PermissionService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName RolePermissionController
 * @Description TODO 角色权限
 * @Author 徐啸儒
 * @Data 2021/8/10 21:13
 * @Version 1.0
 **/
@CrossOrigin
@RestController
public class RolePermissionController {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/sys/menu/menutreeofid")
    public Result getTree(@RequestParam("id") String id){

        if ("".equals(id)||"undefined".equals(id)){
            return Result.success(true);
        }
        //获得树形菜单
        List<PermissionVO> permissionVOS = permissionService.queryAllMenu();

        List<RolePermissionKey> rolePermissionKeys = rolePermissionMapper.listAll(Integer.parseInt(id));
        //封装已有权限id
        List<Integer> ids = new ArrayList<>();

        for (RolePermissionKey key :rolePermissionKeys) {
            ids.add(key.getPermissionId());
        }

        return Result.success(permissionVOS, Arrays.toString(ids.toArray()));
    }


    @PostMapping("sys/roah/editroah")
    public Result update(@RequestBody PermissionTree permissionTree){

        String roleId = permissionTree.getRoleId();

        List<PermissionIds> list = rolePermissionMapper.findPermissionIds(roleId);
        //如果已有权限不为空，清空已有权限
        if (list.size()!=0){
            for (PermissionIds permission:list) {
                RolePermissionKey key = new RolePermissionKey();
                key.setRoleId(Integer.parseInt(roleId));
                key.setPermissionId(Integer.parseInt(permission.getPermissionId()) );
                rolePermissionMapper.deleteByPrimaryKey(key);
            }
        }

        List<String> permissionIds = permissionTree.getAuthIds();
        System.out.println("permissionIds----------"+permissionIds);
        //如果从前端获得的权限不为空，添加角色权限
        if (permissionIds.size()!=0){
            for (String perId:permissionIds) {
                RolePermissionKey key = new RolePermissionKey();
                key.setRoleId(Integer.parseInt(roleId) );
                key.setPermissionId(Integer.parseInt(perId));
                rolePermissionMapper.insertSelective(key);
            }
        }
        return Result.success();

    }



}

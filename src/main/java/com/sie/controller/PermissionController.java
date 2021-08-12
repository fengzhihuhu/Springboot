package com.sie.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sie.DTO.PermissionDTO;
import com.sie.common.Result;
import com.sie.mapper.PermissionMapper;
import com.sie.pojo.Permission;
import com.sie.pojo.User;
import com.sie.service.PermissionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName PermissionController
 * @Description TODO 权限管理
 * @Author 徐啸儒
 * @Data 2021/8/8 15:24
 * @Version 1.0
 **/
@RestController
@RequestMapping("/menu")
@CrossOrigin
public class PermissionController {

    @Autowired
    private PermissionService permissionMapper;

    /*
     * @Author 徐啸儒
     * @Description //TODO 权限查找
     * @Date
     * @Param [current, limit, permissionDTO]
     * @return com.sie.common.Result
    **/
    @RequiresPermissions("permission:list")
    @PostMapping("/findmenu")
    public Result list(@RequestParam(value = "current", required = false, defaultValue = "1") int current,
                       @RequestParam(value = "limit", required = false, defaultValue = "5") int limit,
                       @RequestBody PermissionDTO permissionDTO)  {
        System.out.println("PermissionDTO: "+permissionDTO);
        Map<String, Object> map = new HashMap<>();

        String name = permissionDTO.getName();
        Integer status = permissionDTO.getStatus();
        Integer type = permissionDTO.getTypes();

        map.put("name",name);
        map.put("status",status);
        map.put("type",type);

        PageHelper.startPage(current, limit);
        List<Permission> permissions = permissionMapper.listPermissions(map);
        PageInfo<Permission> pageInfo = new PageInfo<Permission>(permissions);

        return Result.success(pageInfo.getList(),String.valueOf(pageInfo.getTotal()));
    }

    /*
     * @Author 徐啸儒
     * @Description //TODO 权限新增
     * @Date
     * @Param [permission]
     * @return com.sie.common.Result
    **/
    @RequiresPermissions("permission:add")
    @PostMapping(value = "/addmenu")
    public Result add(@RequestBody Permission permission){
        System.out.println("新增："+permission);
        permissionMapper.insertSelective(permission);
        return Result.success();
    }

    /*
     * @Author 徐啸儒
     * @Description //TODO 权限删除
     * @Date
     * @Param [id]
     * @return com.sie.common.Result
    **/
    @RequiresPermissions("permission:delete")
    @DeleteMapping("/delmenu")
    public Result del(){
        return Result.success();
    }

    /*
     * @Author 徐啸儒
     * @Description //TODO 权限更新
     * @Date
     * @Param [permission]
     * @return com.sie.common.Result
    **/
    @RequiresPermissions("permission:update")
    @PostMapping("/editmenu")
    public Result update(@RequestBody Permission permission){
        System.out.println("修改： "+permission);
        int i = permissionMapper.updateByPrimaryKeySelective(permission);
        return  Result.success();
    }

}

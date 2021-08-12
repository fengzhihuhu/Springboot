package com.sie.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sie.DTO.RoleDTO;
import com.sie.common.Result;
import com.sie.mapper.RoleMapper;
import com.sie.mapper.UserMapper;
import com.sie.pojo.Role;
import com.sie.pojo.User;
import com.sie.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName RoleController
 * @Description TODO 角色管理
 * @Author 徐啸儒
 * @Data 2021/8/8 13:14
 * @Version 1.0
 **/
@RestController
@RequestMapping("/role")
@CrossOrigin
public class RoleController {

    @Autowired
    private RoleService roleMapper;

    /*
     * @Author 徐啸儒
     * @Description //TODO 角色查询
     * @Date
     * @Param [current, limit, roleDTO]
     * @return com.sie.common.Result
    **/
    @RequiresPermissions("role:list")
    @PostMapping("/findrole")
    public Result list(@RequestParam(value = "current",required = false,defaultValue = "1") int current,
                       @RequestParam(value = "limit",required = false,defaultValue = "5") int limit,
                       @RequestBody RoleDTO roleDTO){
        System.out.println("roleDTO:  "+roleDTO);
        Map<String, Object> map = new HashMap<>();

        String name = roleDTO.getName();
        Integer status = roleDTO.getStatus();

        map.put("name",name);
        map.put("status",status);

        PageHelper.startPage(current, limit);
        List<Role> roles =  roleMapper.listRoles(map);
        PageInfo<Role> pageInfo = new PageInfo<Role>(roles);
        return Result.success(pageInfo.getList(),String.valueOf(pageInfo.getTotal()));
    }

    /*
     * @Author 徐啸儒
     * @Description //TODO 角色新增
     * @Date
     * @Param [role]
     * @return com.sie.common.Result
    **/
    @RequiresPermissions("role:add")
    @PostMapping("/addrole")
    public Result add(@RequestBody Role role) throws ParseException {
        System.out.println("新增： "+role);
        role.setDeleteFlag(1);
        roleMapper.insertSelective(role);
        return Result.success();
    }

    /*
     * @Author 徐啸儒
     * @Description //TODO 角色删除
     * @Date
     * @Param [id]
     * @return com.sie.common.Result
    **/
    @RequiresPermissions("role:delete")
    @DeleteMapping(value = "/delrole")
    public Result del(/*@RequestParam(value = "id") String id*/){
         //roleMapper.updateDeleteFlag(Integer.parseInt(id));
        return Result.success();
    }

    /*
     * @Author 徐啸儒
     * @Description //TODO 角色更新
     * @Date
     * @Param [role]
     * @return com.sie.common.Result
    **/
    @RequiresPermissions("role:update")
    @PostMapping("/editrole")
    public Result update(@RequestBody Role role){
        System.out.println("修改： "+role);
        role.setDeleteFlag(1);
        roleMapper.updateByPrimaryKeySelective(role);
        return Result.success();
    }



}

package com.sie.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sie.DTO.UserDTO;
import com.sie.common.MD5;
import com.sie.common.Result;
import com.sie.mapper.UserMapper;
import com.sie.pojo.User;
import com.sie.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserService
 * @Description TODO 用户管理
 * @Author 徐啸儒
 * @Data 2021/8/7 12:35
 * @Version 2.0
 **/
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userMapper;

    /*
     * @Author 徐啸儒
     * @Description //TODO 用户查询
     * @Date
     * @Param [current, limit, userDTO]
     * @return com.sie.common.Result
    **/
    @RequiresPermissions("user:list")
    @PostMapping("/finduser")
    public Result pageList(@RequestParam(value = "current", defaultValue = "1") int current,
                           @RequestParam(value = "limit", defaultValue = "5") int limit,
                           @RequestBody UserDTO userDTO) {
        //System.out.println("userDTO: "+userDTO);
        Map<String, Object> map = new HashMap<>();

        String username = userDTO.getUsername();
        String nickname = userDTO.getNickname();
        Integer status = userDTO.getStatus();
        Date createTime = userDTO.getCreateTime();
        Date updateTime = userDTO.getUpdateTime();

        map.put("username", username);
        map.put("nickname", nickname);
        map.put("status", status);
        if (createTime != null) {
            map.put("createTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createTime));
        }
        if (updateTime != null) {
            map.put("updateTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(updateTime));
        }

        PageHelper.startPage(current, limit);
        List<User> userList = userMapper.listUsers(map);//获取所有用户信息
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        return Result.success(pageInfo.getList(), String.valueOf(pageInfo.getTotal()));
    }

    /*
     * @Author 徐啸儒
     * @Description //TODO 新增用户
     * @Date
     * @Param [user]
     * @return com.sie.common.Result
    **/
    @RequiresPermissions("user:add")
    @PostMapping("/adduser")
    public Result add(@RequestBody User user) throws ParseException {

        System.out.println("新增： " + user);
        user.setPassword(MD5.makeMD5(user.getPassword()));
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Date creatTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(format);
        user.setCreateTime(creatTime);
        user.setDeleteFlag(1);
        userMapper.insertSelective(user);
        return Result.success();
    }

    /*
     * @Author 徐啸儒
     * @Description //TODO 用户删除
     * @Date
     * @Param [ids]
     * @return com.sie.common.Result
    **/
    @RequiresPermissions("user:delete")
    @DeleteMapping("/deluser")
    public Result remove(@RequestBody List<String> ids) {
        /*
            User user = userMapper.selectByPrimaryKey(Integer.parseInt(ids.get(i)));
            userMapper.updateStatus(Integer.parseInt(ids.get(i)),user.getStatus());
        }*/
        for (int i = 0; i < ids.size(); i++) {
            userMapper.updateDeleteFlag(Integer.parseInt(ids.get(i)));
        }
        return Result.success();
    }

    /*
     * @Author 徐啸儒
     * @Description //TODO 用户更新
     * @Date
     * @Param [user]
     * @return com.sie.common.Result
    **/
    @RequiresPermissions("user:update")
    @PostMapping("/edituser")
    public Result update(@RequestBody User user) throws ParseException {
        System.out.println("修改：" + user);
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        Date updateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(format);
        user.setUpdateTime(updateTime);
        userMapper.updateByPrimaryKeySelective(user);
        return Result.success();
    }





}
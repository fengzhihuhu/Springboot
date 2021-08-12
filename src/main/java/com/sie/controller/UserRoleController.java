package com.sie.controller;

import com.sie.DTO.Rofu;
import com.sie.DTO.RoleVO;
import com.sie.DTO.UserRoleDTO;
import com.sie.DTO.UserVO;
import com.sie.common.Result;
import com.sie.mapper.RoleMapper;
import com.sie.mapper.UserMapper;
import com.sie.mapper.UserRoleMapper;
import com.sie.pojo.Permission;
import com.sie.pojo.Role;
import com.sie.pojo.UserRoleKey;
import com.sie.service.RoleService;
import com.sie.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @ClassName UserRoleController
 * @Description TODO  用户角色管理
 * @Author 徐啸儒
 * @Data 2021/8/10 8:34
 * @Version 1.0
 **/
@RestController
@CrossOrigin
public class UserRoleController {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserMapper userMapper;
    /*
     * @Author 徐啸儒
     * @Description //TODO 回显
     * @Date  
     * @Param [id] 
     * @return com.sie.common.Result
    **/
    @GetMapping("/sys/rofu/findrofu")
    public Result getIdTreeMenu(@RequestParam("uid") String uid) {
        Map<String, Object> map = new HashMap<>();
        List<Role> roles = roleMapper.listRoles(map);

        ArrayList<Integer> list = new ArrayList<>();

        UserVO userVO = userMapper.findRoles(Integer.parseInt(uid));
        System.out.println("userVO---"+userVO);
        Map map1 = new HashMap<>();
        map1.put("roles",roles);
        //获得已有角色id
        if (userVO!=null){
            Set<RoleVO> roleVOS = userVO.getRoles();
            for (RoleVO roleVO:roleVOS) {
                list.add(roleVO.getId());
            }
        }
        map1.put("list",list);

        return Result.success(map1);
    }

    /*
     * @Author 徐啸儒
     * @Description //TODO 添加
     * @Date  
     * @Param [userRoleDTO] 
     * @return com.sie.common.Result
    **/
    @PostMapping("/sys/rofu/editrofus")
    public Result into(@RequestBody UserRoleDTO userRoleDTO){
        System.out.println("userRoleDTO: "+userRoleDTO);

        String userId = userRoleDTO.getUserId();
        //获得已有的角色
        UserVO userVO = userMapper.findRoles(Integer.parseInt(userId));

        if (userVO!=null){
            Set<RoleVO> roleVOS = userVO.getRoles();
            //如果角色集合不为空，删除已有用户角色关系
            for (RoleVO roleVO : roleVOS) {
                UserRoleKey key = new UserRoleKey();
                Integer roleId = roleVO.getId();
                key.setUserId(Integer.parseInt(userId));
                key.setRoleId(roleId);
                userRoleMapper.deleteByPrimaryKey(key);
            }
        }

        List<Integer> roleIds = userRoleDTO.getRoleIds();
        //如果从前端获得的角色不为空，添加关系
        if (roleIds.size()!=0) {
            for (Integer roleId : roleIds) {
                UserRoleKey key = new UserRoleKey();
                key.setUserId(Integer.parseInt(userId));
                key.setRoleId(roleId);
                userRoleMapper.insertSelective(key);
            }
        }
        return Result.success();
    }


}



package com.sie.service.impl;

import com.sie.DTO.UserRoleDTO;
import com.sie.mapper.UserRoleMapper;
import com.sie.pojo.Role;
import com.sie.pojo.UserRoleKey;
import com.sie.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UserRoleServiceImpl
 * @Description TODO
 * @Author 徐啸儒
 * @Data 2021/8/10 14:51
 * @Version 1.0
 **/
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public int deleteByPrimaryKey(UserRoleKey key) {
        return userRoleMapper.deleteByPrimaryKey(key);
    }

    @Override
    public int insertSelective(UserRoleKey record) {
        return userRoleMapper.insertSelective(record);
    }



}

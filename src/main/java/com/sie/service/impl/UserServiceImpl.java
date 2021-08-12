package com.sie.service.impl;

import com.sie.mapper.UserMapper;
import com.sie.pojo.User;
import com.sie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author 徐啸儒
 * @Data 2021/8/10 14:34
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userMapper.findByUsernameAndPassword(username,password);
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public List<User> listUsers(Map<String, Object> map) {
        return userMapper.listUsers(map);
    }

    @Override
    public int updateDeleteFlag(Integer id) {
        return userMapper.updateDeleteFlag(id);
    }
}

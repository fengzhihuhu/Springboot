package com.sie.service;

import com.sie.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author 徐啸儒
 * @Data 2021/8/9 22:07
 * @Version 1.0
 **/
public interface UserService {

    int deleteByPrimaryKey(Integer id);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    User findByUsernameAndPassword(String username,String password);

    User findByUsername(String username);

    List<User> listUsers(Map<String,Object> map);

    int updateDeleteFlag(Integer id);

}

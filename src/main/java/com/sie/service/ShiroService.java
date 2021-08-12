package com.sie.service;

import com.sie.pojo.SysToken;
import com.sie.pojo.User;

import java.util.Map;

public interface ShiroService {

    /**
     * Find user by username
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * create token by userId
     * @param userId
     * @return
     */
    Map<String,Object> createToken(Integer userId,String token);

    /**
     * logout
     * @param token
     */
    void logout(String token);

    /**
     * find token by token
     * @param accessToken
     * @return
     */
    SysToken findByToken(String accessToken);

    /**
     * find user by userId
     * @param userId
     * @return
     */
    User findByUserId(Integer userId);
}

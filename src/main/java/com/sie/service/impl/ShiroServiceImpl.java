package com.sie.service.impl;

import com.sie.mapper.SysTokenMapper;
import com.sie.mapper.UserMapper;
import com.sie.pojo.SysToken;
import com.sie.pojo.User;
import com.sie.service.ShiroService;
import com.sie.shiro.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ShiroServiceImpl
 * @Description TODO shiro
 * @Author 徐啸儒
 * @Data 2021/8/5 9:36
 * @Version 1.0
 **/
@Service
public class ShiroServiceImpl implements ShiroService {

    //12小时后过期
    private final static int EXPIRE = 3600 * 12;

    @Autowired
    private UserMapper userRepository;

    @Autowired
    private SysTokenMapper sysTokenRepository;

    //根据username查找用户
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    //生成一份token
    @Override
    public Map<String, Object> createToken(Integer userId,String token) {
        Map<String, Object> result = new HashMap<>();
        //生成一个token
        //String token = TokenGenerator.generateValue();
        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime  = new Date(now.getTime() + EXPIRE * 1000);
        //判断是否生成过token
        SysToken tokenEntity = sysTokenRepository.selectByPrimaryKey(userId);
        if (tokenEntity==null){
            tokenEntity = new SysToken();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);
            //保存token
            sysTokenRepository.insertSelective(tokenEntity);
        }else {
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);
            //更新token
            sysTokenRepository.updateByPrimaryKeySelective(tokenEntity);
        }
        //result.put("token",token);
//        result.put("expire",EXPIRE);
        return result;
    }

    @Override
    public void logout(String token) {
        SysToken byToken = sysTokenRepository.findByToken(token);
        sysTokenRepository.deleteByPrimaryKey(byToken.getUserId());

    }

    @Override
    public SysToken findByToken(String accessToken) {
        return  sysTokenRepository.findByToken(accessToken);
    }

    @Override
    public User findByUserId(Integer userId) {
        return userRepository.selectByPrimaryKey(userId);
    }
}

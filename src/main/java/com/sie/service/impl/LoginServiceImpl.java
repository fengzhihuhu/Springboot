package com.sie.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.sie.DTO.LoginDTO;
import com.sie.DTO.LoginInfoDTO;
import com.sie.common.MD5;
import com.sie.mapper.UserMapper;
import com.sie.pojo.User;
import com.sie.service.LoginService;
import com.sie.util.JwtUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LoginServiceImpl
 * @Description TODO 登录
 * @Author 徐啸儒
 * @Data 2021/8/6 16:16
 * @Version 1.0
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    //获得token
    @Override
    public String login(LoginDTO loginDTO) {

        String username = loginDTO.getUsername();
        String password= MD5.makeMD5(loginDTO.getPassword()) ;

        //获取用户信息
        User user = userMapper.findByUsernameAndPassword(username, password);

        if (user==null){
            throw new RuntimeException("用户名或密码错误");
        }

        //使用JWT生成token字符串
        String token = JwtUtils.getJwtToken(String.valueOf(user.getId()),
                user.getUsername());

        return token;
    }

    @Override
    public LoginInfoDTO getLoginInfo(String id) {
        User user = userMapper.selectByPrimaryKey(Integer.parseInt(id));
        //System.out.println(user);
        LoginInfoDTO infoDTO = new LoginInfoDTO();
        BeanUtil.copyProperties(user,infoDTO);
        return infoDTO;
    }


}

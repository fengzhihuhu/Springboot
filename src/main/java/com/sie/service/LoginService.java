package com.sie.service;

import com.sie.DTO.LoginDTO;
import com.sie.DTO.LoginInfoDTO;

/**
 * @ClassName LoginService
 * @Description TODO
 * @Author 徐啸儒
 * @Data 2021/8/6 16:16
 * @Version 1.0
 **/
public interface LoginService {

    String login(LoginDTO loginDTO);

    LoginInfoDTO getLoginInfo(String id);

}

package com.sie.controller;

import com.sie.DTO.LoginDTO;
import com.sie.DTO.LoginInfoDTO;
import com.sie.common.Result;
import com.sie.mapper.UserMapper;
import com.sie.pojo.User;
import com.sie.service.LoginService;
import com.sie.service.ShiroService;
import com.sie.util.JwtUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserController
 * @Description TODO 登录
 * @Author 徐啸儒
 * @Data 2021/8/6 10:35
 * @Version 1.0
 **/
@CrossOrigin
@RestController
public class LoginController {

    private final ShiroService shiroService;

    public LoginController(ShiroService shiroService) {
        this.shiroService = shiroService;
    }

    @Autowired
    private LoginService loginService;

    /*
     * @Author 徐啸儒
     * @Description //TODO 用户登录
     * @Date
     * @Param [loginDTO]
     * @return com.sie.common.Result
    **/
    @PostMapping("/public/user/login")
    public Result login(@RequestBody @Validated LoginDTO loginDTO){

        //用户信息
        User user = shiroService.findByUsername(loginDTO.getUsername());

        Map<String, Object> map = new HashMap<>();

        String token = loginService.login(loginDTO);
        map.put("token",token);

        //生成token，并保存到数据库
        shiroService.createToken(user.getId(),token);

        return Result.success(token);
    }

    @GetMapping("/public/user/info")
    public Result getLoginInfo(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        try {
            String userId = JwtUtils.getMemberIdByJwtToken(request);
            LoginInfoDTO loginInfo = loginService.getLoginInfo(userId);
            map.put("item",loginInfo);
            return Result.success(map);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("error");
        }
    }

    /*
     * @Author 徐啸儒
     * @Description //TODO 用户退出
     * @Date
     * @Param [token]
     * @return com.sie.common.Result
    **/
    @PostMapping("/public/user/logout")
    public Result logout(@RequestHeader("token") String token){
        shiroService.logout(token);
        return Result.success(null,"您已安全退出系统");
    }

    @RequiresPermissions("user:list") //没有的话 AuthorizationException
    @GetMapping("/user:list")
    public Map<String, Object> select(String token) {
        System.out.println("select");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        map.put("msg", "当前用户有select的权力");
        return map;
    }

    @RequiresRoles("svip") //没有的话 AuthorizationException
    @GetMapping("/aa")
    public Map<String, Object> vip() {
        System.out.println("vip");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 200);
        map.put("msg", "当前用户有VIP角色");
        return map;
    }

}

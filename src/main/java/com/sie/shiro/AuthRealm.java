package com.sie.shiro;

import com.sie.DTO.RoleVO;
import com.sie.mapper.RoleMapper;
import com.sie.mapper.UserMapper;
import com.sie.pojo.Permission;
import com.sie.pojo.Role;
import com.sie.pojo.SysToken;
import com.sie.pojo.User;
import com.sie.service.ShiroService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @ClassName AuthRealm
 * @Description TODO 认证授权
 * @Author 徐啸儒
 * @Data 2021/8/5 9:22
 * @Version 1.0
 **/
@Component
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private ShiroService shiroService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    //授权 获取用户的角色和权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //1. 从 PrincipalCollection 中来获取登录用户的信息
        User user = (User)principalCollection.getPrimaryPrincipal();
        //2.添加角色和权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Set<RoleVO> roles = userMapper.findRoles(user.getId()).getRoles();
        //System.out.println("角色:  "+roles);
        //如果角色不为空
        if (roles.size()!=0){
            for (RoleVO role:roles) {
                //2.1添加角色
                info.addRole(role.getName());

                RoleVO permissions1 = roleMapper.findPermissions(role.getId());
                System.out.println("permissions1----"+permissions1);
                Set<Permission> permissions =permissions1.getPermissions();
                //如果权限不为空
                if (permissions.size()!=0){
                    System.out.println("权限:  "+permissions);
                    for (Permission permission:permissions) {
                        //2.1.1添加权限
                        info.addStringPermission(permission.getUrl());
                    }

                }
            }

        }
        return info;
    }

    //认证 判断token的有效性
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取token，既前端传入的token
        String accessToken  = (String)authenticationToken.getPrincipal();
        //1. 根据accessToken，查询用户信息
        SysToken tokenEntity  = shiroService.findByToken(accessToken);
        //2. token失效
        if (tokenEntity==null||tokenEntity.getExpireTime().getTime()<System.currentTimeMillis()){
            throw new IncorrectCredentialsException("token失效,请重新登录");
        }
        //3. 调用数据库的方法, 从数据库中查询 username 对应的用户记录
        User user = shiroService.findByUserId(tokenEntity.getUserId());
        //4. 若用户不存在, 则可以抛出 UnknownAccountException 异常
        if(user==null){
            throw new UnknownAccountException("用户不存在");
        }
        //5. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, this.getName());
        return info;
    }
}

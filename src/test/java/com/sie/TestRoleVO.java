package com.sie;

import com.sie.DTO.RoleVO;
import com.sie.DTO.UserVO;
import com.sie.mapper.RoleMapper;
import com.sie.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName TestRoleVO
 * @Description TODO
 * @Author 徐啸儒
 * @Data 2021/8/9 10:13
 * @Version 1.0
 **/
@SpringBootTest
public class TestRoleVO {

    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private RoleMapper roleMapper;

    @Test
    public void testroles(){
        UserVO roles = userMapper.findRoles(27);
        System.out.println(roles);

    }
    
    @Test
    public void testrole(){
        RoleVO permissions = roleMapper.findPermissions(7);
        System.out.println(permissions);

    }


}

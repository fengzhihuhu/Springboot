package com.sie.service;

import com.sie.DTO.UserRoleDTO;
import com.sie.pojo.Role;
import com.sie.pojo.UserRoleKey;

import java.util.List;

public interface UserRoleService {

    int deleteByPrimaryKey(UserRoleKey key);

    int insertSelective(UserRoleKey record);


}

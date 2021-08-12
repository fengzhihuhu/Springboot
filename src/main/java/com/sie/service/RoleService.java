package com.sie.service;

import com.sie.pojo.Role;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface RoleService {


    int insertSelective(Role record);

    int updateByPrimaryKeySelective(Role record);

    List<Role> listRoles(Map<String,Object> map);

    int updateDeleteFlag(Integer id);
}

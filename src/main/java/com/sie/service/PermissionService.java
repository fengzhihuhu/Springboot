package com.sie.service;

import com.sie.DTO.PermissionVO;
import com.sie.pojo.Permission;

import java.util.List;
import java.util.Map;

public interface PermissionService {

    int insertSelective(Permission record);

    int updateByPrimaryKeySelective(Permission record);

    List<Permission> listPermissions(Map map);

    List<PermissionVO> queryAllMenu();

}

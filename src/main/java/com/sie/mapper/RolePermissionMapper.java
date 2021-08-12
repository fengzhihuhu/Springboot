package com.sie.mapper;

import com.sie.DTO.PermissionIds;
import com.sie.pojo.RolePermissionKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface RolePermissionMapper {
    int deleteByPrimaryKey(RolePermissionKey key);

    int insert(RolePermissionKey record);

    int insertSelective(RolePermissionKey record);

    List<RolePermissionKey> listAll(Integer id);

    List<PermissionIds> findPermissionIds(String roleId);

}
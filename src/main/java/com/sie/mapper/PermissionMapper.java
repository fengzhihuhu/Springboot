package com.sie.mapper;

import com.sie.DTO.PermissionVO;
import com.sie.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Mapper
@Component
public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<Permission> listPermissions(Map map);

    List<PermissionVO> listAll();

}
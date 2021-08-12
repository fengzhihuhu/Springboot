package com.sie.mapper;

import com.sie.DTO.Rofu;
import com.sie.DTO.UserRoleDTO;
import com.sie.pojo.Role;
import com.sie.pojo.UserRoleKey;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserRoleMapper {
    int deleteByPrimaryKey(UserRoleKey key);

    int insert(UserRoleKey record);

    int insertSelective(UserRoleKey record);

    /*UserRoleDTO findRoleIds(Integer id);

    List<Role> findRoles(Integer id);*/

    //List<Rofu> listRofus(Integer uid);



}
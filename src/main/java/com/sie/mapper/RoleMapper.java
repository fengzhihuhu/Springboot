package com.sie.mapper;

import com.sie.DTO.RoleVO;
import com.sie.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> listRoles(Map<String,Object> map);

    @Update(" UPDATE `role` set delete_flag=2  WHERE id = #{id} ")
    int updateDeleteFlag(Integer id);

    RoleVO findPermissions(Integer rid);
}
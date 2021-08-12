package com.sie.mapper;

import com.sie.DTO.UserVO;
import com.sie.pojo.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    @Select("SELECT * FROM `user` WHERE username = #{username} and password=#{password}")
    User findByUsernameAndPassword(String username,String password);

    @Select("SELECT * FROM `user` WHERE username = #{username}")
    User findByUsername(String username);

    List<User> listUsers(Map<String,Object> map);

    int updateStatus(Integer id,Integer status);

    @Update(" UPDATE `user` set delete_flag=2  WHERE id = #{id} ")
    int updateDeleteFlag(Integer id);

    UserVO findRoles(Integer id);



}
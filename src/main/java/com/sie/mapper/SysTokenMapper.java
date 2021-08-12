package com.sie.mapper;

import com.sie.pojo.SysToken;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface SysTokenMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(SysToken record);

    int insertSelective(SysToken record);

    SysToken selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(SysToken record);

    int updateByPrimaryKey(SysToken record);

    SysToken findByToken(String accessToken);
}

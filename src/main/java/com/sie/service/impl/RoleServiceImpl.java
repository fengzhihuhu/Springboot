package com.sie.service.impl;

import com.sie.mapper.RoleMapper;
import com.sie.pojo.Role;
import com.sie.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName RoleService
 * @Description TODO
 * @Author 徐啸儒
 * @Data 2021/8/10 14:37
 * @Version 1.0
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public int insertSelective(Role record) {
        return roleMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(Role record) {
        return roleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<Role> listRoles(Map<String, Object> map) {
        return roleMapper.listRoles(map);
    }

    @Override
    public int updateDeleteFlag(Integer id) {
        return roleMapper.updateDeleteFlag(id);
    }
}

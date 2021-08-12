package com.sie.service.impl;

import com.sie.DTO.PermissionVO;
import com.sie.mapper.PermissionMapper;
import com.sie.pojo.Permission;
import com.sie.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName PermissionServiceImpl
 * @Description TODO
 * @Author 徐啸儒
 * @Data 2021/8/10 14:39
 * @Version 1.0
 **/
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public int insertSelective(Permission record) {
        return permissionMapper.insertSelective(record);
    }

    @Override
    public int updateByPrimaryKeySelective(Permission record) {
        return permissionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<Permission> listPermissions(Map map) {
        return permissionMapper.listPermissions(map);
    }

    @Override
    public List<PermissionVO> queryAllMenu() {

        List<PermissionVO> permissionVOS = permissionMapper.listAll();

        List<PermissionVO> list = buildMenu(permissionVOS);

        return list;
    }

    private List<PermissionVO> buildMenu(List<PermissionVO> menuList) {
        //遍历list, upper为0的 设置level 1
        List<PermissionVO> TreeNode = new ArrayList<>();
        for (PermissionVO node: menuList) {
            if (node.getParentId()==0){
                node.setLevel(1);
                TreeNode.add(selectChildren(node,menuList));
            }
        }
        return TreeNode;

    }

    private PermissionVO  selectChildren(PermissionVO node,List<PermissionVO> TreeNode){

        node.setChildren(new ArrayList<PermissionVO>());
        for (PermissionVO it:TreeNode) {
            if (node.getId().equals(it.getParentId())){
                int level=node.getLevel()+1;
                it.setLevel(level);
                //如果Children为空,初始化
                if (node.getChildren()==null){
                    node.setChildren(new ArrayList<PermissionVO>());
                }
                //将查询出来的子菜单放到父菜单中
                node.getChildren().add(selectChildren(it,TreeNode));

            }

        }
        return node;
    }

}

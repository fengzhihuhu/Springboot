package com.sie.DTO;

import lombok.Data;

import java.util.List;

@Data
public class PermissionVO {
    private Integer id;

    private String name;

    private String url;

    private Integer parentId;

    private Integer type;
    //层级
    private Integer level;

    private List<PermissionVO> children;


}
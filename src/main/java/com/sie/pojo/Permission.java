package com.sie.pojo;

import lombok.Data;

@Data
public class Permission {
    private Integer id;

    private String name;

    private String description;

    private String url;

    private String perms;

    private Integer parentId;

    private Integer type;

    private Integer orderNum;

    private String icon;

    private Integer status;

}
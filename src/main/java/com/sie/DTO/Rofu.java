package com.sie.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName Rofu
 * @Description TODO
 * @Author 徐啸儒
 * @Data 2021/8/10 16:15
 * @Version 1.0
 **/
@Data
public class Rofu {

    private Integer userId;

    private Integer roleId;

    private String code;

    private String roleName;

    private Integer status;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    boolean isSelect=false;

}

package com.sie.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName UserDTO
 * @Description TODO
 * @Author 徐啸儒
 * @Data 2021/8/7 14:09
 * @Version 1.0
 **/
@Data
public class UserDTO {

    private String username;
    private String nickname;
    private Integer status;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
    private int id;

}

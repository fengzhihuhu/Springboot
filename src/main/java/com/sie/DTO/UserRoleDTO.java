package com.sie.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @ClassName UserRoleDTO
 * @Description TODO
 * @Author 徐啸儒
 * @Data 2021/8/10 8:51
 * @Version 1.0
 **/
@Data
public class UserRoleDTO {

    private String userId;
    private List<Integer> roleIds;

}

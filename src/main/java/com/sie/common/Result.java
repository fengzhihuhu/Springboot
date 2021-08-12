package com.sie.common;

import lombok.Data;

/*
 * @Author 徐啸儒
 * @Description //TODO 返回结果类型
 * @Date
 * @Param
 * @return
**/
@Data
public class Result {
    private String code;
    private String msg;
    private Object data;

    public Result() {
    }

    public Result(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result success(Object data){
        return new Result("200","操作成功",data);
    }

    public static Result success(){
        return new Result("200","操作成功",null);
    }

    public static Result success(Object data, String msg){
        return new Result("200",msg,data);
    }

    public static Result error(String code,String msg){
        return new Result(code,msg,null);
    }


}

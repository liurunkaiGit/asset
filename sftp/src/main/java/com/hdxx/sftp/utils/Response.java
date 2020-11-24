package com.hdxx.sftp.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @author: liurunkai
 * @Date: 2019/12/31 14:06
 */
@Data
@NoArgsConstructor
public class Response {

    private int status;
    private String message;
    private Object result;

    @Getter
    @AllArgsConstructor
    public enum ResponseStatusEnum {
        SUCCESS(200, "成功"),
        FAIL(500, "失败");
        private Integer status;
        private String message;
    }

    public Response(int status, String message, Object result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public static Response success(Integer status, String message, Object result) {
        return new Response(status, message, result);
    }


    public static Response success(Object result) {
        return new Response(ResponseStatusEnum.SUCCESS.getStatus(), ResponseStatusEnum.SUCCESS.getMessage(), result);
    }

    public static Response failure(Object result) {
        return new Response(ResponseStatusEnum.FAIL.getStatus(), ResponseStatusEnum.FAIL.getMessage(), result);
    }

    public static Response failure(String message, Object result) {
        return new Response(ResponseStatusEnum.FAIL.getStatus(), message, result);
    }

}


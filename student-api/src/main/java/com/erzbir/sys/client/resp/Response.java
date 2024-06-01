package com.erzbir.sys.client.resp;


import cn.hutool.http.HttpStatus;

import java.io.Serializable;

/**
 * @author Erzbir
 * @Data: 2024/5/29 09:29
 */

public class Response<T> implements Serializable {

    private Boolean success;

    private Integer code;

    private String msg;

    private T data;

    public Response(boolean success, int code, String msg, T data) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Response() {

    }

    public static <T> Response<T> blank() {
        return new Response<>();
    }

    public static <T> Response<T> ok(T data) {
        return new Response<>(true, HttpStatus.HTTP_OK, "success", data);
    }

    public static <T> Response<T> ok(T data, String msg) {
        return new Response<>(true, HttpStatus.HTTP_OK, msg, data);
    }

    public static <T> Response<T> ok(T data, Integer code, String msg) {
        return new Response<>(true, code, msg, data);
    }

    public static <T> Response<T> error(String msg) {
        return new Response<>(false, HttpStatus.HTTP_BAD_REQUEST, msg, null);
    }

    public static <T> Response<T> error(T data, String msg) {
        return new Response<>(false, HttpStatus.HTTP_BAD_REQUEST, msg, data);
    }

    public static <T> Response<T> error(Integer code, String msg) {
        return new Response<>(false, code, msg, null);
    }

    public static <T> Response<T> error(T data, Integer code, String msg) {
        return new Response<>(false, code, msg, data);
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

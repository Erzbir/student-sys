package com.erzbir.backend.util;

import cn.hutool.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Erzbir
 * @Data: 2024/5/29 09:29
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> implements Serializable {

    private Boolean success;

    private Integer code;

    private String msg;

    private T data;

    public static String toJson(Response<?> response) {
        return """
                {
                "success": "%s",
                "msg": "%s",
                "code": %s,
                "data"" "%s"
                }
                """.formatted(response.getSuccess(), response.getMsg(), response.getCode(), response.getData());
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
}

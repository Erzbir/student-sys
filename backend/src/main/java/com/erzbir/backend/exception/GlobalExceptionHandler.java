package com.erzbir.backend.exception;

import com.erzbir.backend.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Erzbir
 * @Data: 2024/5/29 11:21
 */
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public Response<Object> badRequestException(BadRequestException e) {
        log.error("错误请求: {}", e.getMessage());
        return Response.error(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Response<Object> exception(Exception e) {
        log.error("发生错误: {}", e.getMessage());
        return Response.error(e.getMessage());
    }
}

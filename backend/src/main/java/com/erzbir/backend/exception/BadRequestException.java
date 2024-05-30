package com.erzbir.backend.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Erzbir
 * @Data: 2024/5/29 11:21
 */
public class BadRequestException extends RuntimeException {
    private int code = HttpStatus.BAD_REQUEST.value();
    private final String message = HttpStatus.BAD_REQUEST.getReasonPhrase();

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }

    public BadRequestException(int code, String message) {
        super(message);
        this.code = code;
    }
}

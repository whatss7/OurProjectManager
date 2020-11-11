package com.ourprojmgr.demo.exception;

import org.springframework.http.HttpStatus;

/**
 * 业务异常
 * <p/>
 * 抛出的 BusinessException 将被 GlobalExceptionHandler 捕获，
 * 然后自动设置 HTTP 响应码和 ApiResponse JSON
 *
 * @author 朱华彬
 */
public class BusinessException extends RuntimeException {

    private final BusinessErrorType errorType;

    public BusinessException(BusinessErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    public BusinessErrorType getErrorType() {
        return errorType;
    }

}

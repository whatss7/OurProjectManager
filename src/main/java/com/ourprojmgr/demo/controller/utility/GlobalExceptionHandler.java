package com.ourprojmgr.demo.controller.utility;

import com.ourprojmgr.demo.exception.BusinessErrorType;
import com.ourprojmgr.demo.exception.BusinessException;
import com.ourprojmgr.demo.jsonmodel.ApiResponseJson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 *
 * @author 朱华彬
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponseJson> businessExceptionHandler(HttpServletRequest request, BusinessException e) {
        var response = new ApiResponseJson(
                e.getErrorType().getType(), e.getMessage());
        return new ResponseEntity<>(response, e.getErrorType().getStatus());
    }

    /**
     * 非预期的异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseJson> unexpectedExceptionHandler(HttpServletRequest request, Exception e) {
        var response = new ApiResponseJson(
                BusinessErrorType.UNKNOWN_ERROR.getType(), e.getMessage());
        return new ResponseEntity<>(response, BusinessErrorType.UNKNOWN_ERROR.getStatus());
    }
}

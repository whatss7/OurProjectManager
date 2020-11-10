package com.ourprojmgr.demo.controller.utility;

import com.ourprojmgr.demo.jsonmodel.ApiResponseJson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 其他非预期的异常
     * @author 朱华彬
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseJson> exceptionHandler(HttpServletRequest request, Exception e) {
        ApiResponseJson response = new ApiResponseJson(
                ApiResponseJson.TYPE_UNKNOWN_ERROR, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

package com.ourprojmgr.demo.jsonmodel;

import com.ourprojmgr.demo.exception.BusinessErrorType;

/**
 * 返回的响应消息
 *
 * @author 朱华彬
 */
public class ApiResponseJson {
    private final String type;
    private final String message;

    public ApiResponseJson(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "type='" + type + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

}

package com.ourprojmgr.demo.jsonmodel;

/**
 * 返回的响应消息
 * @author 朱华彬
 */
public class ApiResponseJson {
    private String type;
    private String message;

    public ApiResponseJson(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "type='" + type + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

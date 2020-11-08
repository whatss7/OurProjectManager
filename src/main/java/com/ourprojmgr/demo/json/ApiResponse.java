package com.ourprojmgr.demo.json;

public class ApiResponse {
    private String type;
    private String message;

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

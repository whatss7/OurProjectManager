package com.ourprojmgr.demo.jsonmodel;

/**
 * 返回的响应消息
 *
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

    /**
     * 用户名或密码错误
     */
    public static final String TYPE_WRONG_PASSWORD_OR_USERNAME = "WrongPasswordOrUsername";
    /**
     * 旧密码错误
     */
    public static final String TYPE_WRONG_OLD_PASSWORD = "WrongOldPassword";
    /**
     * 同名用户已存在
     */
    public static final String TYPE_USER_ALREADY_EXIST = "UserAlreadyExist";
    /**
     * 用户不存在
     */
    public static final String TYPE_USER_NOT_FOUND = "UserNotFound";
    /**
     * 通知不存在
     */
    public static final String TYPE_NOTIFICATION_NOT_FOUND = "NotificationNotFound";
    /**
     * 未登录
     */
    public static final String TYPE_NOT_LOGIN="NotLogin";
    /**
     * 没有权限
     */
    public static final String TYPE_PERMISSION_DENIED="PermissionDenied";
    /**
     * 项目不存在
     */
    public static final String TYPE_PROJECT_NOT_FOUND = "ProjectNotFound";
    /**
     * 任务不存在
     */
    public static final String TYPE_TASK_NOT_FOUND = "TaskNotFound";
    /**
     * 评论不存在
     */
    public static final String TYPE_COMMENT_NOT_FOUND = "CommentNotFound";
    /**
     * 成员不存在
     */
    public static final String TYPE_MEMBER_NOT_FOUND = "MemberNotFound";
    /**
     * 未知错误
     */
    public static final String TYPE_UNKNOWN_ERROR = "UnknownError";
}

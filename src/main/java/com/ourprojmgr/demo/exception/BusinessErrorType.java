package com.ourprojmgr.demo.exception;

import org.springframework.http.HttpStatus;

/**
 * 业务错误枚举类，包含错误类型、HTTP 响应代码两个部分
 */
public enum BusinessErrorType {
    /**
     * 用户名或密码错误
     */
    WRONG_PASSWORD_OR_USERNAME("WrongPasswordOrUsername", HttpStatus.UNAUTHORIZED),
    /**
     * 旧密码错误
     */
    WRONG_OLD_PASSWORD("WrongOldPassword", HttpStatus.FORBIDDEN),
    /**
     * 同名用户已存在
     */
    USER_ALREADY_EXIST("UserAlreadyExist", HttpStatus.CONFLICT),
    /**
     * 用户不存在
     */
    USER_NOT_FOUND("UserNotFound", HttpStatus.NOT_FOUND),
    /**
     * 通知不存在
     */
    NOTIFICATION_NOT_FOUND("NotificationNotFound", HttpStatus.NOT_FOUND),
    /**
     * 未登录
     */
    NOT_LOGIN("NotLogin", HttpStatus.UNAUTHORIZED),
    /**
     * 没有权限
     */
    PERMISSION_DENIED("PermissionDenied", HttpStatus.FORBIDDEN),
    /**
     * 项目不存在
     */
    PROJECT_NOT_FOUND("ProjectNotFound", HttpStatus.NOT_FOUND),
    /**
     * 任务不存在
     */
    TASK_NOT_FOUND("TaskNotFound", HttpStatus.NOT_FOUND),
    /**
     * 评论不存在
     */
    COMMENT_NOT_FOUND("CommentNotFound", HttpStatus.NOT_FOUND),
    /**
     * 成员不存在
     */
    MEMBER_NOT_FOUND("MemberNotFound", HttpStatus.NOT_FOUND),
    /**
     * 未知错误
     */
    UNKNOWN_ERROR("UnknownError", HttpStatus.INTERNAL_SERVER_ERROR),
    /**
     * 邀请不存在
     */
    INVITATION_NOT_FOUND("InvitationNotFound", HttpStatus.NOT_FOUND),
    /**
     * 邀请已存在
     */
    INVITATION_ALREADY_EXIST("InvitationAlreadyExist", HttpStatus.CONFLICT),
    /**
     * 邀请接收者已在项目中
     */
    RECEIVER_ALREADY_IN_PROJECT("ReceiverAlreadyInProject", HttpStatus.CONFLICT);


    private final String type;
    private final HttpStatus status;

    BusinessErrorType(String type, HttpStatus status) {
        this.type = type;
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "BusinessErrorType{" +
                "type='" + type + '\'' +
                ", status=" + status +
                '}';
    }
}

package com.ourprojmgr.demo.dbmodel;

import java.time.LocalDateTime;

public class Invitation {
    private int id;
    private LocalDateTime createAt;
    private LocalDateTime endAt;
    private String status;
    private int senderId;
    private int receiverId;
    private int projectId;

    /**
     * （项目管理员）已创建
     */
    public static final String STATUS_CREATED = "created";
    /**
     * （项目管理员）已取消
     */
    public static final String STATUS_CANCELED = "canceled";
    /**
     * （被邀请者） 已接受
     */
    public static final String STATUS_ACCEPTED = "accepted";
    /**
     * （被邀请者）已拒绝
     */
    public static final String STATUS_REJECTED = "rejected";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

    public void setEndAt(LocalDateTime endAt) {
        this.endAt = endAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public static String getStatusCreated() {
        return STATUS_CREATED;
    }

    public static String getStatusCanceled() {
        return STATUS_CANCELED;
    }

    public static String getStatusAccepted() {
        return STATUS_ACCEPTED;
    }

    public static String getStatusRejected() {
        return STATUS_REJECTED;
    }

    @Override
    public String toString() {
        return "Invitation{" +
                "id=" + id +
                ", createAt=" + createAt +
                ", endAt=" + endAt +
                ", status='" + status + '\'' +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", projectId=" + projectId +
                '}';
    }

}

package com.ourprojmgr.demo.jsonmodel;

import java.time.LocalDateTime;

/**
 * 表示邀请的 JSON
 * @author 朱华彬
 */
public class InvitationJson {
    private int id;
    private LocalDateTime createAt;
    private LocalDateTime endAt;
    private String status;
    private UserJson sender;
    private UserJson receiver;
    private String projectUrl;

    public static final String STATUS_CREATED = "created";
    public static final String STATUS_CANCELS = "canceled";
    public static final String STATUS_ACCEPTED = "accepted";
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

    public UserJson getSender() {
        return sender;
    }

    public void setSender(UserJson sender) {
        this.sender = sender;
    }

    public UserJson getReceiver() {
        return receiver;
    }

    public void setReceiver(UserJson receiver) {
        this.receiver = receiver;
    }

    public String getProjectUrl() {
        return projectUrl;
    }

    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl;
    }

    @Override
    public String toString() {
        return "Invitation{" +
                "id=" + id +
                ", createAt=" + createAt +
                ", endAt=" + endAt +
                ", status='" + status + '\'' +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", projectUrl='" + projectUrl + '\'' +
                '}';
    }
}

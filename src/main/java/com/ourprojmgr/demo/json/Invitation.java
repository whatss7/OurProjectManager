package com.ourprojmgr.demo.json;

import java.time.LocalDateTime;

public class Invitation {
    private long id;
    private LocalDateTime createAt;
    private LocalDateTime endAt;
    private String status;
    private User sender;
    private User receiver;
    private String projectUrl;

    public static final String STATUS_CREATED = "created";
    public static final String STATUS_CANCELS = "canceled";
    public static final String STATUS_ACCEPTED = "accepted";
    public static final String STATUS_REJECTED = "rejected";

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
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

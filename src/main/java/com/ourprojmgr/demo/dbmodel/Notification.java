package com.ourprojmgr.demo.dbmodel;

import java.time.LocalDateTime;

/**
 * 通知实体类
 */
public class Notification {
    private int id;
    private boolean read;
    private String title;
    private String body;
    private LocalDateTime createAt;
    private Integer senderId;
    private Integer receiverId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", read=" + read +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", createAt=" + createAt +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                '}';
    }
}

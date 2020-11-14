package com.ourprojmgr.demo.jsonmodel;

import java.time.LocalDateTime;

/**
 * 表示通知的 JSON
 * @author 朱华彬
 */
public class NotificationJson {
    private int id;
    private boolean read;
    private String title;
    private String body;
    private LocalDateTime createAt;
    private UserJson sender;
    private UserJson receiver;

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

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", isRead=" + read +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", createAt=" + createAt +
                ", sender=" + sender +
                ", receiver=" + receiver +
                '}';
    }
}

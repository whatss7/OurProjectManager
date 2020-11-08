package com.ourprojmgr.demo.json;

import java.time.LocalDateTime;

public class Notification {
    private long id;
    private boolean isRead;
    private String title;
    private String body;
    private LocalDateTime createAt;
    private User sender;
    private User receiver;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
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

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", isRead=" + isRead +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", createAt=" + createAt +
                ", sender=" + sender +
                ", receiver=" + receiver +
                '}';
    }
}

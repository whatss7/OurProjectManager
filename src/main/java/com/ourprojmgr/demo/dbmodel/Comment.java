package com.ourprojmgr.demo.dbmodel;

import java.time.LocalDateTime;

/**
 * 评论实体类
 */
public class Comment {
    private int id;
    private int taskId;
    private String body;
    private LocalDateTime createAt;
    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", taskId=" + taskId +
                ", body='" + body + '\'' +
                ", createAt=" + createAt +
                ", userId=" + userId +
                '}';
    }
}

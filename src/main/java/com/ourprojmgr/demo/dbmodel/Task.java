package com.ourprojmgr.demo.dbmodel;

import java.time.LocalDateTime;

/**
 * 任务实体类
 */
public class Task {
    private int id;
    private int projectId;
    private String title;
    private String body;
    private LocalDateTime createAt;
    private int creatorId;
    private boolean complete;
    private LocalDateTime completeAt;
    private int completerId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
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

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public LocalDateTime getCompleteAt() {
        return completeAt;
    }

    public void setCompleteAt(LocalDateTime completeAt) {
        this.completeAt = completeAt;
    }

    public int getCompleterId() {
        return completerId;
    }

    public void setCompleterId(int completerId) {
        this.completerId = completerId;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", createAt=" + createAt +
                ", creatorId=" + creatorId +
                ", complete=" + complete +
                ", completeAt=" + completeAt +
                ", completerId=" + completerId +
                '}';
    }
}

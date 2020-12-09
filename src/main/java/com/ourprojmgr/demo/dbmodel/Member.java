package com.ourprojmgr.demo.dbmodel;

import java.time.LocalDateTime;

/**
 * 成员实体类
 */
public class Member {
    private int userId;
    private int projectId;
    private String role;
    private LocalDateTime joinAt;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getJoinAt() {
        return joinAt;
    }

    public void setJoinAt(LocalDateTime joinAt) {
        this.joinAt = joinAt;
    }

    @Override
    public String toString() {
        return "Member{" +
                "userId=" + userId +
                ", projectId=" + projectId +
                ", role='" + role + '\'' +
                ", joinAt=" + joinAt +
                '}';
    }
}

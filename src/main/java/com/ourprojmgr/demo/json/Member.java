package com.ourprojmgr.demo.json;

import java.time.LocalDateTime;

public class Member {
    private User user;
    private String role;
    private LocalDateTime joinAt;

    public static final String ROLE_SUPER_ADMIN = "SuperAdmin";
    public static final String ROLE_ADMIN = "Admin";
    public static final String ROLE_MEMBER = "Member";

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
                "user=" + user +
                ", role='" + role + '\'' +
                ", joinAt=" + joinAt +
                '}';
    }
}

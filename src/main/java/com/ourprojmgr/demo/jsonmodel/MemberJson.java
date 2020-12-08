package com.ourprojmgr.demo.jsonmodel;

import java.time.LocalDateTime;

/**
 * 表示项目成员的 JSON
 *
 * @author 朱华彬
 */
public class MemberJson {
    private UserJson user;
    private String role;
    private LocalDateTime joinAt;

    public static final String ROLE_SUPER_ADMIN = "SuperAdmin";
    public static final String ROLE_ADMIN = "Admin";
    public static final String ROLE_MEMBER = "Member";

    public UserJson getUser() {
        return user;
    }

    public void setUser(UserJson user) {
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

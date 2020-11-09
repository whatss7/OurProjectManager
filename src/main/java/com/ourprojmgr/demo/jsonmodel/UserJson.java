package com.ourprojmgr.demo.jsonmodel;

import java.time.LocalDateTime;

/**
 * 表示用户信息的 JSON
 * @author 朱华彬
 */
public class UserJson {
    private int id;
    private String username;
    private String nickname;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private int projectCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public int getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(int projectCount) {
        this.projectCount = projectCount;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", projectCount=" + projectCount +
                '}';
    }
}

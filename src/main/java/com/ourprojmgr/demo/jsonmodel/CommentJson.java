package com.ourprojmgr.demo.jsonmodel;

import java.time.LocalDateTime;

/**
 * 表示评论的 JSON
 * @author 朱华彬
 */
public class CommentJson {
    private int id;
    private String body;
    private LocalDateTime createAt;
    private UserJson user;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", createAt=" + createAt +
                ", user=" + user +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public UserJson getUser() {
        return user;
    }

    public void setUser(UserJson user) {
        this.user = user;
    }
}

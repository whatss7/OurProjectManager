package com.ourprojmgr.demo.json;

import java.time.LocalDateTime;
import java.util.List;

public class Task {
    private long id;
    private String title;
    private String body;
    private int commentNum;
    private LocalDateTime createAt;
    private User creator;
    private List<User> executors;
    private boolean isComplete;
    private LocalDateTime completeAt;
    private User completer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<User> getExecutors() {
        return executors;
    }

    public void setExecutors(List<User> executors) {
        this.executors = executors;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public LocalDateTime getCompleteAt() {
        return completeAt;
    }

    public void setCompleteAt(LocalDateTime completeAt) {
        this.completeAt = completeAt;
    }

    public User getCompleter() {
        return completer;
    }

    public void setCompleter(User completer) {
        this.completer = completer;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", commentNum=" + commentNum +
                ", createAt=" + createAt +
                ", creator=" + creator +
                ", executors=" + executors +
                ", isComplete=" + isComplete +
                ", completeAt=" + completeAt +
                ", completer=" + completer +
                '}';
    }
}

package com.ourprojmgr.demo.jsonmodel;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 表示任务的 JSON
 *
 * @author 朱华彬
 */
public class TaskJson {
    private int id;
    private String title;
    private String body;
    private int commentNum;
    private LocalDateTime createAt;
    private UserJson creator;
    private List<UserJson> executors;
    private boolean complete;
    private LocalDateTime completeAt;
    private UserJson completer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public UserJson getCreator() {
        return creator;
    }

    public void setCreator(UserJson creator) {
        this.creator = creator;
    }

    public List<UserJson> getExecutors() {
        return executors;
    }

    public void setExecutors(List<UserJson> executors) {
        this.executors = executors;
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

    public UserJson getCompleter() {
        return completer;
    }

    public void setCompleter(UserJson completer) {
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
                ", isComplete=" + complete +
                ", completeAt=" + completeAt +
                ", completer=" + completer +
                '}';
    }
}

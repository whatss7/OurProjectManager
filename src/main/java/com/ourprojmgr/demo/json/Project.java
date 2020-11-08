package com.ourprojmgr.demo.json;

import java.time.LocalDateTime;
import java.util.List;

public class Project {
    private long id;
    private String name;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private User superAdmin;
    private List<User> admins;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public User getSuperAdmin() {
        return superAdmin;
    }

    public void setSuperAdmin(User superAdmin) {
        this.superAdmin = superAdmin;
    }

    public List<User> getAdmins() {
        return admins;
    }

    public void setAdmins(List<User> admins) {
        this.admins = admins;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", superAdmin=" + superAdmin +
                ", admins=" + admins +
                '}';
    }
}

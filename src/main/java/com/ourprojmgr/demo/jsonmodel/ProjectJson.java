package com.ourprojmgr.demo.jsonmodel;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 表示项目的 JSON
 * @author 朱华彬
 */
public class ProjectJson {
    private int id;
    private String name;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private UserJson superAdmin;
    private List<UserJson> admins;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public UserJson getSuperAdmin() {
        return superAdmin;
    }

    public void setSuperAdmin(UserJson superAdmin) {
        this.superAdmin = superAdmin;
    }

    public List<UserJson> getAdmins() {
        return admins;
    }

    public void setAdmins(List<UserJson> admins) {
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

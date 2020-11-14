package com.ourprojmgr.demo.dbmodel;

import java.time.LocalDateTime;

<<<<<<< Updated upstream
public class Project {
    private int id;
=======
/**
 * 项目实体类
 */
public class Project {
    private Integer id;
>>>>>>> Stashed changes
    private String name;
    private String description;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

<<<<<<< Updated upstream
    public int getId() {
        return id;
    }

    public void setId(int id) {
=======
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
>>>>>>> Stashed changes
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
}

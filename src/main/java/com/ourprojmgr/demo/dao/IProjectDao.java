package com.ourprojmgr.demo.dao;

import com.ourprojmgr.demo.dbmodel.Comment;
import com.ourprojmgr.demo.dbmodel.Project;
import com.ourprojmgr.demo.dbmodel.Task;
import com.ourprojmgr.demo.dbmodel.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IProjectDao {
    @Select("select role from Member where userId = #{userId} and projectId = #{projectId}")
    String getUserRole(int projectId, int userId);

    @Select("select * from Comment where taskId = #{taskId}")
    List<Comment> getTaskComments(int taskId);

    @Select("select * from Task where id = #{taskId}")
    Task getTaskById(int taskId);

    @Select("select * from Comment where id = #{commentId} and taskId = #{taskId}")
    Comment getTaskComment(int taskId, int commentId);

    @Insert("insert into Comment(taskId, body, createAt, userId) values(#{taskId}, #{body}, #{createAt}, #{userId})")
    void insertComment(Comment comment);

    @Select("select * from Comment where id = last_insert_id()")
    Comment getLastInsertComment();

    @Delete("delete from Comment where id = #{commentId}")
    void deleteComment(int commentId);

    @Select("select * from Project where id = #{id}")
    Project getProjectById(int id);
}

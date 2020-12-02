package com.ourprojmgr.demo.dao;

import com.ourprojmgr.demo.dbmodel.Comment;
import com.ourprojmgr.demo.dbmodel.Project;
import com.ourprojmgr.demo.dbmodel.Task;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IProjectDao {
    /**
     * 获取用户在某项目的角色
     * @param projectId 项目id
     * @param userId 用户id
     * @return String 角色
     */
    @Select("select role from Member where userId = #{userId} and projectId = #{projectId}")
    String getUserRole(int projectId, int userId);

    /**
     * 获取某任务下的所有评论
     * @param taskId 任务id
     * @return List<Comment> 任务下的所有评论
     */
    @Select("select * from Comment where taskId = #{taskId}")
    List<Comment> getTaskComments(int taskId);

    /**
     * 根据id获取任务
     * @param taskId 任务id
     * @return Task 任务
     */
    @Select("select * from Task where id = #{taskId}")
    Task getTaskById(int taskId);

    /**
     * 获取任务下的某条评论
     * @param taskId 任务id
     * @param commentId 评论id
     * @return Comment 评论
     */
    @Select("select * from Comment where id = #{commentId} and taskId = #{taskId}")
    Comment getTaskComment(int taskId, int commentId);

    /**
     * 保存评论
     * @param comment 新评论
     */
    @Insert("insert into Comment(taskId, body, createAt, userId) values(#{taskId}, #{body}, #{createAt}, #{userId})")
    void insertComment(Comment comment);

    /**
     * 获取最近一次保存的评论
     * @return Comment 最近一次保存的评论
     */
    @Select("select * from Comment where id = last_insert_id()")
    Comment getLastInsertComment();

    /**
     * 删除评论
     * @param commentId 要删除的评论的id
     */
    @Delete("delete from Comment where id = #{commentId}")
    void deleteComment(int commentId);

    /**
     * 根据id获取项目
     * @param id 项目id
     * @return Project 项目
     */
    @Select("select * from Project where id = #{id}")
    Project getProjectById(int id);
}

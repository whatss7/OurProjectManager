package com.ourprojmgr.demo.service;

import com.ourprojmgr.demo.dbmodel.*;
import com.ourprojmgr.demo.jsonmodel.CommentJson;
import com.ourprojmgr.demo.jsonmodel.InvitationJson;
import com.ourprojmgr.demo.jsonmodel.ProjectJson;
import com.ourprojmgr.demo.jsonmodel.TaskJson;

import java.util.List;

/**
 * 与项目有关的业务逻辑
 */
public interface IProjectService {

    /**
     * 按照 ID 获取项目
     *
     * @param id 项目 ID
     * @return Project 实体类
     */
    Project getProjectById(int id);

    /**
     * 将 DB Model 的 Project 转换为 JSON Model
     */
    ProjectJson projectToJson(Project project);

    /**
     * 创建一个新的项目
     * @param project 要创建的项目信息
     * @return 创建的项目
     */
    Project createProject(Project project);

    void updateProject(Project project);

    void deleteProject(int projectId);

    /**
     * 是否为项目的 Admin
     */
    boolean isSuperAdminOf(User user, Project project);

    /**
     * 是否为项目的 Admin（或 SuperAdmin）
     */
    boolean isAdminOf(User user, Project project);

    /**
     * 是否为项目的 Member（或 Admin、SuperAdmin）
     */
    boolean isMemberOf(User user, Project project);

    List<User> getMembers(Project project);

    void addMember(User user, Project project, String role);

    void deleteMember(User user, Project project);

    /**
     * 按 ID 获取邀请
     *
     * @param id 邀请的 ID
     * @return 若邀请不存在，则返回 null
     */
    Invitation getInvitationById(int id);

    /**
     * 将 DB Model 的 Invitation 转换为 JSON Model
     */
    InvitationJson invitationToJson(Invitation invitation);

    /**
     * 发送邀请
     *
     * @param sender   发送者
     * @param Receiver 接收者
     * @param project  邀请加入的项目
     * @return Invitation 实体类
     * @throws com.ourprojmgr.demo.exception.BusinessException 出现以下几种错误则抛异常：
     *                                                         <ol>
     *                                                             <li>sender 不是 admin</li>
     *                                                             <li>receiver 已在项目中</li>
     *                                                             <li>相同的邀请已存在（根据 sender、receiver、project 判断）</li>
     *                                                         </ol>
     */
    Invitation sendInvitation(User sender, User Receiver, Project project);

    Invitation getInvitationByReceiver(Project project, User receiver);

    /**
     * 获取项目中所有的邀请
     */
    List<Invitation> getInvitations(Project project);

    /**
     * 接受邀请
     *
     * @param user       邀请的收件人
     * @param invitation 邀请
     * @throws com.ourprojmgr.demo.exception.BusinessException 出现以下几种错误则抛异常：
     *                                                         <ol>
     *                                                             <li>user 并非该邀请的 receiver</li>
     *                                                             <li>邀请的状态并非 created</li>
     *                                                         </ol>
     */
    void acceptInvitation(User user, Invitation invitation);

    /**
     * 拒绝邀请
     *
     * @param user       邀请的收件人
     * @param invitation 邀请
     * @throws com.ourprojmgr.demo.exception.BusinessException 出现以下几种错误则抛异常：
     *                                                         <ol>
     *                                                             <li>user 并非该邀请的 receiver</li>
     *                                                             <li>邀请的状态并非 created</li>
     *                                                         </ol>
     */
    void rejectInvitation(User user, Invitation invitation);

    /**
     * 取消邀请
     *
     * @param admin      项目管理员
     * @param invitation 邀请
     * @throws com.ourprojmgr.demo.exception.BusinessException 出现以下几种情况则抛异常：
     *                                                         <ol>
     *                                                             <li>用户并非项目的 Admin</li>
     *                                                             <li>邀请的状态并非 created</li>
     *                                                         </ol>
     */
    void cancelInvitation(User admin, Invitation invitation);

    /**
     * 获取某项任务下的所有评论
     * @param taskId 任务id
     * @return List<CommentJson> 任务下的所有评论的Json
     */
    List<CommentJson> getTaskCommentJsons(int taskId);

    /**
     * 按照id获取任务
     * @param taskId 任务id
     * @return Task 任务
     */
    Task getTaskById(int taskId, int projectId);

    List<Task> getProjectTasks(int projectId);

    void deleteTask(int taskId);

    List<User> getExecutors(int taskId);

    /**
     * 获取某项任务下的某条评论
     * @param taskId 任务id
     * @param commentId 评论id
     * @return CommentJson 评论的Json
     */
    CommentJson getTaskCommentJson(int taskId, int commentId);

    void updateTask(Task task);

    TaskJson taskToJson(Task task);

    Task createTask(Task task);

    void addExecutor(int taskId, int executorId);

    void deleteExecutor(int taskId, int executorId);

    /**
     * 保存评论
     * @param comment 新评论
     * @return CommentJson 新评论的Json
     */
    CommentJson saveComment(Comment comment);

    /**
     * 删除评论
     * @param commentId 评论id
     */
    void deleteComment(int commentId);

    /**
     * 将 DB Model 的 Comment 转换为 JSON Model
     * @param comment DB Model 的 Comment
     * @return JSON Model
     */
    CommentJson commentToJson(Comment comment);

    //请自行添加其他方法
}

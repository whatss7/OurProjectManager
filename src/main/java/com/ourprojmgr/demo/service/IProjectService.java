package com.ourprojmgr.demo.service;

import com.ourprojmgr.demo.dbmodel.Invitation;
import com.ourprojmgr.demo.dbmodel.Project;
import com.ourprojmgr.demo.dbmodel.User;
import com.ourprojmgr.demo.jsonmodel.InvitationJson;
import com.ourprojmgr.demo.jsonmodel.ProjectJson;

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

    /**
     * 发送邀请
     * TODO 抛异常：sender 不是 admin、receiver 已在项目中、邀请已存在
     *
     * @param sender   发送者
     * @param Receiver 接收者
     * @param project  邀请加入的项目
     * @return Invitation 实体类
     */
    Invitation sendInvitation(User sender, User Receiver, Project project);

    /**
     * 将 DB Model 的 Invitation 转换为 JSON Model
     */
    InvitationJson invitationToJson(Invitation invitation);

    /**
     * 获取项目中所有的邀请
     */
    List<Invitation> getInvitations(Project project);

    /**
     * 按 ID 获取邀请
     * @param id 邀请的 ID
     * @return 若邀请不存在，则返回 null
     */
    Invitation getInvitationById(int id);

    //请自行添加其他方法
}

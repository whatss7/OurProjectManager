package com.ourprojmgr.demo.service.impl;

import com.ourprojmgr.demo.dao.IProjectDao;
import com.ourprojmgr.demo.dao.IUserDao;
import com.ourprojmgr.demo.dbmodel.*;
import com.ourprojmgr.demo.jsonmodel.CommentJson;
import com.ourprojmgr.demo.jsonmodel.InvitationJson;
import com.ourprojmgr.demo.jsonmodel.ProjectJson;
import com.ourprojmgr.demo.jsonmodel.UserJson;
import com.ourprojmgr.demo.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * IProjectService 的实现类
 *
 * @author 谢可睿
 */
@Service
public class ProjectServiceImpl implements IProjectService {
    @Autowired
    IProjectDao projectDao;

    @Autowired
    IUserDao userDao;

    @Override
    public Project getProjectById(int id) {
        return projectDao.getProjectById(id);
    }

    @Override
    public ProjectJson projectToJson(Project project) {
        return null;
    }

    @Override
    public boolean isSuperAdminOf(User user, Project project) {
        String role = projectDao.getUserRole(project.getId(), user.getId());
        return (role != null) && role.equals("SuperAdmin");
    }

    @Override
    public boolean isAdminOf(User user, Project project) {
        String role = projectDao.getUserRole(project.getId(), user.getId());
        return (role != null) && (role.equals("SuperAdmin") || role.equals("Admin"));
    }

    @Override
    public boolean isMemberOf(User user, Project project) {
        String role = projectDao.getUserRole(project.getId(), user.getId());
        return (role != null) && (role.equals("SuperAdmin") || role.equals("Admin") || role.equals("Member"));
    }

    @Override
    public Invitation getInvitationById(int id) {
        return null;
    }

    @Override
    public InvitationJson invitationToJson(Invitation invitation) {
        return null;
    }

    @Override
    public Invitation sendInvitation(User sender, User Receiver, Project project) {
        return null;
    }

    @Override
    public List<Invitation> getInvitations(Project project) {
        return null;
    }

    @Override
    public void acceptInvitation(User user, Invitation invitation) {

    }

    @Override
    public void rejectInvitation(User user, Invitation invitation) {

    }

    @Override
    public void cancelInvitation(User admin, Invitation invitation) {

    }

    @Override
    public List<CommentJson> getTaskCommentJsons(int taskId) {
        List<Comment> comments = projectDao.getTaskComments(taskId);
        List<CommentJson> commentJsons = new ArrayList<CommentJson>();
        for(Comment comment:comments){
            commentJsons.add(commentToJson(comment));
        }
        return commentJsons;
    }

    @Override
    public Task getTaskById(int taskId) {
        return projectDao.getTaskById(taskId);
    }

    @Override
    public CommentJson getTaskCommentJson(int taskId, int commentId) {
        return commentToJson(projectDao.getTaskComment(taskId, commentId));
    }

    @Override
    public CommentJson saveComment(Comment comment) {
        projectDao.insertComment(comment);
        return commentToJson(projectDao.getLastInsertComment());
    }

    @Override
    public void deleteComment(int commentId) {
        projectDao.deleteComment(commentId);
    }

    @Override
    public CommentJson commentToJson(Comment comment) {
        if(comment == null){
            return null;
        }
        CommentJson commentJson = new CommentJson();
        commentJson.setId(comment.getId());
        commentJson.setBody(comment.getBody());
        commentJson.setCreateAt(comment.getCreateAt());
        commentJson.setUser(userToJson(userDao.getUserById(comment.getUserId())));
        return commentJson;
    }

    private UserJson userToJson(User user){
        if(user == null){
            return null;
        }
        UserJson userJson = new UserJson();
        userJson.setId(user.getId());
        userJson.setUsername(user.getUsername());
        userJson.setNickname(user.getNickname());
        userJson.setCreateAt(user.getCreateAt());
        userJson.setUpdateAt(user.getUpdateAt());
        userJson.setProjectCount(userDao.countProjectByUserId(user.getId()));
        return userJson;
    }
}

package com.ourprojmgr.demo.service.impl;

import com.ourprojmgr.demo.dbmodel.Invitation;
import com.ourprojmgr.demo.dbmodel.Project;
import com.ourprojmgr.demo.dbmodel.User;
import com.ourprojmgr.demo.jsonmodel.InvitationJson;
import com.ourprojmgr.demo.jsonmodel.ProjectJson;
import com.ourprojmgr.demo.service.IProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * IProjectService 的实现类
 *
 * @author 谢可睿
 */
@Service
public class ProjectServiceImpl implements IProjectService {
    @Override
    public Project getProjectById(int id) {
        return null;
    }

    @Override
    public ProjectJson projectToJson(Project project) {
        return null;
    }

    @Override
    public boolean isSuperAdminOf(User user, Project project) {
        return false;
    }

    @Override
    public boolean isAdminOf(User user, Project project) {
        return false;
    }

    @Override
    public boolean isMemberOf(User user, Project project) {
        return false;
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
}

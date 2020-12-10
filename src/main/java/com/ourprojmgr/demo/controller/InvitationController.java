package com.ourprojmgr.demo.controller;

import com.ourprojmgr.demo.controller.utility.CurrentUser;
import com.ourprojmgr.demo.controller.utility.LoginRequired;
import com.ourprojmgr.demo.dbmodel.Invitation;
import com.ourprojmgr.demo.dbmodel.Project;
import com.ourprojmgr.demo.dbmodel.User;
import com.ourprojmgr.demo.exception.BusinessErrorType;
import com.ourprojmgr.demo.exception.BusinessException;
import com.ourprojmgr.demo.jsonmodel.InvitationJson;
import com.ourprojmgr.demo.service.IProjectService;
import com.ourprojmgr.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理与邀请有关的请求
 *
 * @author 朱华彬
 */
@RestController
@RequestMapping("/api/projects/{projectId}/invitations")
public class InvitationController {

    private final IUserService userService;
    private final IProjectService projectService;

    @Autowired
    public InvitationController(IUserService userService,
                                IProjectService projectService) {
        this.userService = userService;
        this.projectService = projectService;
    }

    /**
     * 发送邀请
     *
     * @param projectId  项目 ID
     * @param invitation 请求体中的 JSON
     * @param user       当前用户
     * @return 邀请的 JSON
     * @throws BusinessException 业务异常
     * @author 朱华彬
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @LoginRequired
    public InvitationJson sendInvitations(@PathVariable Integer projectId,
                                          @RequestBody InvitationJson invitation,
                                          @CurrentUser User user) {
        Project project = getProjectOrThrow(projectId);
        checkAdminOrThrow(user, project);
        int receiverId = invitation.getReceiver().getId();
        User receiver = userService.getUserById(receiverId);
        if (receiver == null) {
            //接收者不存在
            throw new BusinessException(BusinessErrorType.USER_NOT_FOUND,
                    "Receiver with id " + receiverId + " not found.");
        }
        return projectService.invitationToJson(
                projectService.sendInvitation(user, receiver, project));
    }

    /**
     * 获取项目中已发送的所有邀请
     *
     * @param projectId 项目 ID
     * @param user      当前用户
     * @return 邀请列表
     * @throws BusinessException 业务异常
     * @author 朱华彬
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @LoginRequired
    public List<InvitationJson> getInvitations(@PathVariable Integer projectId,
                                               @CurrentUser User user) {
        Project project = getProjectOrThrow(projectId);
        checkAdminOrThrow(user, project);
        List<InvitationJson> jsonList = new ArrayList<>();
        for (Invitation invitation : projectService.getInvitations(project)) {
            jsonList.add(projectService.invitationToJson(invitation));
        }
        return jsonList;
    }


    /**
     * 获取某个邀请
     *
     * @param projectId 项目 ID
     * @param id        邀请 ID
     * @param user      当前用户
     * @return 一个邀请 JSON
     * @throws BusinessException 业务异常
     * @author 朱华彬
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @LoginRequired
    public InvitationJson getInvitation(@PathVariable Integer projectId,
                                        @PathVariable Integer id,
                                        @CurrentUser User user) {
        Project project = getProjectOrThrow(projectId);
        Invitation invitation = getInvitationOrThrow(id);
        if (user.getId() != invitation.getReceiverId()) {
            checkAdminOrThrow(user, project);
        }
        return projectService.invitationToJson(invitation);
    }

    /**
     * 取消邀请
     *
     * @param id   邀请 ID
     * @param user 当前用户
     * @throws BusinessException 业务异常
     * @author 朱华彬
     */
    @GetMapping("/{id}/cancel")
    @ResponseStatus(HttpStatus.OK)
    @LoginRequired
    public void cancelInvitation(@PathVariable Integer id,
                                 @CurrentUser User user) {
        Invitation invitation = getInvitationOrThrow(id);
        projectService.cancelInvitation(user, invitation);
    }

    /**
     * 接受邀请
     *
     * @param id   邀请 ID
     * @param user 当前用户
     * @author 朱华彬
     */
    @GetMapping("/{id}/accept")
    @ResponseStatus(HttpStatus.OK)
    @LoginRequired
    public void acceptInvitation(@PathVariable Integer id,
                                 @CurrentUser User user) {
        Invitation invitation = getInvitationOrThrow(id);
        projectService.acceptInvitation(user, invitation);
    }

    /**
     * 拒绝邀请
     *
     * @param id   邀请 ID
     * @param user 当前用户
     * @author 朱华彬
     */
    @GetMapping("/{id}/reject")
    @ResponseStatus(HttpStatus.OK)
    @LoginRequired
    public void rejectInvitation(@PathVariable Integer id,
                                 @CurrentUser User user) {
        Invitation invitation = getInvitationOrThrow(id);
        projectService.rejectInvitation(user, invitation);
    }

    // ---------------------- 以下为辅助方法 ----------------------

    /**
     * 获取 Invitation，若不存在则抛异常
     *
     * @param id 邀请 ID
     * @return Invitation 实体类
     * @throws BusinessException 邀请不存在
     * @author 朱华彬
     */
    private Invitation getInvitationOrThrow(int id) {
        Invitation invitation = projectService.getInvitationById(id);
        if (invitation == null) {
            throw new BusinessException(BusinessErrorType.INVITATION_NOT_FOUND,
                    "Invitation with id " + id + "not found.");
        }
        return invitation;
    }

    /**
     * 获取 Project，若不存在则抛异常
     *
     * @param id 项目 ID
     * @return Project 实体类
     * @throws BusinessException 项目不存在
     * @author 朱华彬
     */
    private Project getProjectOrThrow(int id) {
        Project project = projectService.getProjectById(id);
        if (project == null) {
            throw new BusinessException(BusinessErrorType.PROJECT_NOT_FOUND,
                    "Project with id " + id + " not found.");
        }
        return project;
    }

    /**
     * 若不是本项目的 Admin，则抛异常
     *
     * @param user    用户
     * @param project 项目
     * @throws BusinessException 不是 Admin
     * @author 朱华彬
     */
    private void checkAdminOrThrow(User user, Project project) {
        if (!projectService.isAdminOf(user, project)) {
            //不是本项目的 Admin
            throw new BusinessException(BusinessErrorType.PERMISSION_DENIED, "Not Admin");
        }
    }

}

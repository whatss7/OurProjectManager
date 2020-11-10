package com.ourprojmgr.demo.controller;

import com.ourprojmgr.demo.controller.utility.CurrentUser;
import com.ourprojmgr.demo.controller.utility.LoginRequired;
import com.ourprojmgr.demo.dbmodel.Invitation;
import com.ourprojmgr.demo.dbmodel.Project;
import com.ourprojmgr.demo.dbmodel.User;
import com.ourprojmgr.demo.jsonmodel.ApiResponseJson;
import com.ourprojmgr.demo.jsonmodel.InvitationJson;
import com.ourprojmgr.demo.jsonmodel.ProjectJson;
import com.ourprojmgr.demo.jsonmodel.UserJson;
import com.ourprojmgr.demo.service.IProjectService;
import com.ourprojmgr.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理与邀请有关的 API
 */
@RestController
@RequestMapping("/api/projects/{projectId}/invitations")
public class InvitationController {
    //TODO di
    private IUserService userService;

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    //TODO di
    private IProjectService projectService;

    public void setProjectService(IProjectService projectService) {
        this.projectService = projectService;
    }

    /**
     * 发送邀请
     *
     * @param projectId  项目 ID
     * @param invitation 请求体中的 JSON
     * @param user       当前用户
     * @return 邀请的 JSON
     * @author 朱华彬
     */
    @PostMapping
    @LoginRequired
    public ResponseEntity<?> sendInvitations(
            @PathVariable Integer projectId,
            @RequestBody InvitationJson invitation,
            @CurrentUser User user) {
        Project project = projectService.getProjectById(projectId);
        if (project == null) {
            //项目不存在
            ApiResponseJson apiResponse = new ApiResponseJson(
                    ApiResponseJson.TYPE_PROJECT_NOT_FOUND,
                    "Project with id " + projectId + " not found."
            );
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }

        int receiverId = invitation.getReceiver().getId();
        User receiver = userService.getUserById(
                invitation.getReceiver().getId());
        if (receiver == null) {
            //接收者不存在
            ApiResponseJson apiResponse = new ApiResponseJson(
                    ApiResponseJson.TYPE_USER_NOT_FOUND,
                    "Receiver with id " + receiverId + " not found.");
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }

        invitation = projectService.invitationToJson(
                projectService.sendInvitation(user, receiver, project));
        return new ResponseEntity<>(invitation, HttpStatus.CREATED);
    }

    /**
     * 获取项目中的邀请
     *
     * @param projectId 项目 ID
     * @param user      当前用户
     * @return 邀请列表
     * @author 朱华彬
     */
    @GetMapping
    @LoginRequired
    public ResponseEntity<?> getInvitations(@PathVariable Integer projectId,
                                            @CurrentUser User user) {
        Project project = projectService.getProjectById(projectId);
        if (project == null) {
            //项目不存在
            var apiResponse = new ApiResponseJson(
                    ApiResponseJson.TYPE_PROJECT_NOT_FOUND,
                    "Project with id " + projectId + " not found."
            );
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }

        if (!projectService.isAdminOf(user, project)) {
            //不是本项目的 Admin
            var apiResponse = new ApiResponseJson(
                    ApiResponseJson.TYPE_PERMISSION_DENIED,
                    "Not Admin"
            );
            return new ResponseEntity<>(apiResponse, HttpStatus.FORBIDDEN);
        }

        List<InvitationJson> jsonList = new ArrayList<>();
        for (Invitation invitation : projectService.getInvitations(project)) {
            jsonList.add(projectService.invitationToJson(invitation));
        }
        return new ResponseEntity<>(jsonList, HttpStatus.OK);
    }



}

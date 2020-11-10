package com.ourprojmgr.demo.controller;

import com.ourprojmgr.demo.controller.utility.CurrentUser;
import com.ourprojmgr.demo.controller.utility.LoginRequired;
import com.ourprojmgr.demo.dbmodel.Invitation;
import com.ourprojmgr.demo.dbmodel.Project;
import com.ourprojmgr.demo.dbmodel.User;
import com.ourprojmgr.demo.jsonmodel.ApiResponseJson;
import com.ourprojmgr.demo.jsonmodel.InvitationJson;
import com.ourprojmgr.demo.service.IProjectService;
import com.ourprojmgr.demo.service.IUserService;
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

        if (!projectService.isAdminOf(user, project)) {
            //不是本项目的 Admin
            var apiResponse = new ApiResponseJson(
                    ApiResponseJson.TYPE_PERMISSION_DENIED,
                    "Not Admin"
            );
            return new ResponseEntity<>(apiResponse, HttpStatus.FORBIDDEN);
        }

        invitation = projectService.invitationToJson(
                projectService.sendInvitation(user, receiver, project));
        return new ResponseEntity<>(invitation, HttpStatus.CREATED);
    }

    /**
     * 获取项目中已发送的所有邀请
     *
     * @param projectId 项目 ID
     * @param user      当前用户
     * @return 邀请列表 JSON
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


    /**
     * 获取某个邀请
     *
     * @param projectId 项目 ID
     * @param id        邀请 ID
     * @param user      当前用户
     * @return 一个邀请 JSON
     * @author 朱华彬
     */
    @GetMapping("/{id}")
    @LoginRequired
    public ResponseEntity<?> getInvitation(@PathVariable Integer projectId,
                                           @PathVariable Integer id,
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

        Invitation invitation = projectService.getInvitationById(id);
        if (invitation == null) {
            //邀请不存在
            var apiResponse = new ApiResponseJson(
                    ApiResponseJson.TYPE_INVITATION_NOT_FOUND,
                    "Invitation with id " + id + "not found."
            );
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
        InvitationJson json = projectService.invitationToJson(invitation);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    /**
     * 取消邀请
     *
     * @param id   邀请 ID
     * @param user 当前用户
     * @author 朱华彬
     */
    @GetMapping("/{id}/canceled")
    @LoginRequired
    public ResponseEntity<?> cancelInvitation(@PathVariable Integer id, @CurrentUser User user) {
        Invitation invitation = projectService.getInvitationById(id);
        if (invitation == null) {
            //邀请不存在
            var apiResponse = new ApiResponseJson(
                    ApiResponseJson.TYPE_INVITATION_NOT_FOUND,
                    "Invitation with id " + id + "not found."
            );
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
        projectService.cancelInvitation(user, invitation);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 接受邀请
     *
     * @param id   邀请 ID
     * @param user 当前用户
     * @author 朱华彬
     */
    @GetMapping("/{id}/accept")
    @LoginRequired
    public ResponseEntity<?> acceptInvitation(@PathVariable Integer id, @CurrentUser User user) {
        Invitation invitation = projectService.getInvitationById(id);
        if (invitation == null) {
            //邀请不存在
            var apiResponse = new ApiResponseJson(
                    ApiResponseJson.TYPE_INVITATION_NOT_FOUND,
                    "Invitation with id " + id + "not found."
            );
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
        projectService.acceptInvitation(user, invitation);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 拒绝邀请
     *
     * @param id   邀请 ID
     * @param user 当前用户
     * @author 朱华彬
     */
    @GetMapping("/{id}/reject")
    @LoginRequired
    public ResponseEntity<?> rejectInvitation(@PathVariable Integer id, @CurrentUser User user) {
        Invitation invitation = projectService.getInvitationById(id);
        if (invitation == null) {
            //邀请不存在
            var apiResponse = new ApiResponseJson(
                    ApiResponseJson.TYPE_INVITATION_NOT_FOUND,
                    "Invitation with id " + id + "not found."
            );
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
        projectService.rejectInvitation(user, invitation);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

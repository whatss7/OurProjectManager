package com.ourprojmgr.demo.controller;

import com.ourprojmgr.demo.controller.utility.CurrentUser;
import com.ourprojmgr.demo.controller.utility.LoginRequired;
import com.ourprojmgr.demo.dbmodel.Project;
import com.ourprojmgr.demo.dbmodel.User;
import com.ourprojmgr.demo.exception.BusinessErrorType;
import com.ourprojmgr.demo.exception.BusinessException;
import com.ourprojmgr.demo.jsonmodel.MemberJson;
import com.ourprojmgr.demo.jsonmodel.ProjectJson;
import com.ourprojmgr.demo.jsonmodel.UserJson;
import com.ourprojmgr.demo.service.IProjectService;
import com.ourprojmgr.demo.service.IUserService;
import com.ourprojmgr.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 处理与项目有关的请求
 *
 * @author 谢可睿
 */
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final IProjectService projectService;
    private final IUserService userService;

    @Autowired
    public ProjectController(IProjectService projectService, IUserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @LoginRequired
    public ProjectJson createProject(
            @RequestBody ProjectJson projectJson,
            @CurrentUser User user){
        Project project = new Project();
        project.setName(projectJson.getName());
        project.setDescription(projectJson.getDescription());
        project.setCreateAt(LocalDateTime.now());
        project.setUpdateAt(LocalDateTime.now());
        projectService.createProject(project);
        projectService.addMember(user,project,"SuperAdmin");
        return projectService.projectToJson(project);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProjectJson getProject(@PathVariable Integer id){
        Project project = getProjectOrThrow(id);
        return projectService.projectToJson(project);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @LoginRequired
    public void updateProject(
            @PathVariable Integer id,
            @RequestBody ProjectJson projectJson,
            @CurrentUser User user){
        Project project = getProjectOrThrow(id);
        throwIfNotAdmin(user, project);
        project.setName(projectJson.getName());
        project.setDescription(projectJson.getDescription());
        project.setUpdateAt(LocalDateTime.now());
        projectService.updateProject(project);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @LoginRequired
    public void deleteProject(
            @PathVariable Integer id,
            @CurrentUser User user) {
        Project project = getProjectOrThrow(id);
        throwIfNotAdmin(user, project);
        projectService.deleteProject(project.getId());
    }

    @GetMapping("/{projectId}/members")
    @ResponseStatus(HttpStatus.OK)
    @LoginRequired
    public List<UserJson> getMembers(
            @PathVariable Integer projectId,
            @CurrentUser User user) {
        Project project = getProjectOrThrow(projectId);
        throwIfNotMember(user, project);
        List<User> users = projectService.getMembers(project);
        List<UserJson> jsons = new ArrayList<UserJson>();
        users.forEach(u -> jsons.add(userService.userToJson(u)));
        return jsons;
    }

    @PatchMapping("/{projectId}/members/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @LoginRequired
    public void updateMember(
            @PathVariable Integer projectId,
            @PathVariable Integer userId,
            @RequestBody MemberJson memberJson,
            @CurrentUser User user) {
        Project project = getProjectOrThrow(projectId);
        User opUser = getUserOrThrow(userId);
        throwIfNotSuperAdmin(user, project);
        if(!projectService.isMemberOf(opUser, project)){
            throw new BusinessException(BusinessErrorType.MEMBER_NOT_FOUND,
                    "User with id " + userId + "is not the member of project " + projectId);
        }
        if(memberJson.getRole().equals("SuperAdmin")){
            projectService.deleteMember(user, project);
            projectService.addMember(user, project, "Admin");
        }
        projectService.deleteMember(opUser, project);
        projectService.addMember(opUser, project, memberJson.getRole());
    }

    @DeleteMapping("/{projectId}/members/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @LoginRequired
    public void deleteMember(
            @PathVariable Integer projectId,
            @PathVariable Integer userId,
            @CurrentUser User user) {
        Project project = getProjectOrThrow(projectId);
        User opUser = getUserOrThrow(userId);
        throwIfNotAdmin(user, project);
        if(projectService.isSuperAdminOf(opUser, project)){
            throw new BusinessException(BusinessErrorType.PERMISSION_DENIED,
                    "Can't kick the super admin of the project. Super admins must transfer or delete the project before quitting.");
        } else if(projectService.isAdminOf(opUser, project)){
            throwIfNotSuperAdmin(user, project);
        } else if(projectService.isMemberOf(opUser, project)){
            throwIfNotAdmin(user, project);
        } else {
            throw new BusinessException(BusinessErrorType.MEMBER_NOT_FOUND,
                    "User with id " + userId + "is not the member of project " + projectId);
        }
        projectService.deleteMember(opUser, project);
    }

    private void throwIfNotMember(User user, Project project){
        if(projectService.isMemberOf(user, project)){
            throw new BusinessException(BusinessErrorType.PERMISSION_DENIED,
                    "User " + user.getNickname() + " is not the member of " + project.getName() + ".");
        }
    }

    private void throwIfNotAdmin(User user, Project project){
        if(projectService.isAdminOf(user, project)){
            throw new BusinessException(BusinessErrorType.PERMISSION_DENIED,
                    "User " + user.getNickname() + " is not the admin of " + project.getName() + ".");
        }
    }

    private void throwIfNotSuperAdmin(User user, Project project){
        if(projectService.isSuperAdminOf(user, project)){
            throw new BusinessException(BusinessErrorType.PERMISSION_DENIED,
                    "User " + user.getNickname() + " is not the super admin of " + project.getName() + ".");
        }
    }

    private Project getProjectOrThrow(int id) {
        Project project = projectService.getProjectById(id);
        if (project == null) {
            throw new BusinessException(BusinessErrorType.PROJECT_NOT_FOUND,
                    "Project with id " + id + " not found.");
        }
        return project;
    }

    private User getUserOrThrow(int id) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new BusinessException(BusinessErrorType.USER_NOT_FOUND,
                    "User with id " + id + " not found.");
        }
        return user;
    }
}

package com.ourprojmgr.demo.controller;

import com.ourprojmgr.demo.controller.utility.CurrentUser;
import com.ourprojmgr.demo.controller.utility.LoginRequired;
import com.ourprojmgr.demo.dbmodel.*;
import com.ourprojmgr.demo.exception.BusinessErrorType;
import com.ourprojmgr.demo.exception.BusinessException;
import com.ourprojmgr.demo.jsonmodel.*;
import com.ourprojmgr.demo.service.IProjectService;
import com.ourprojmgr.demo.service.IUserService;
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
            @CurrentUser User user) {
        Project project = new Project();
        project.setName(projectJson.getName());
        project.setDescription(projectJson.getDescription());
        project.setCreateAt(LocalDateTime.now());
        project.setUpdateAt(LocalDateTime.now());
        project = projectService.createProject(project);
        projectService.addMember(user, project, "SuperAdmin");
        return projectService.projectToJson(project);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProjectJson getProject(@PathVariable Integer id) {
        Project project = getProjectOrThrow(id);
        return projectService.projectToJson(project);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @LoginRequired
    public void updateProject(
            @PathVariable Integer id,
            @RequestBody ProjectJson projectJson,
            @CurrentUser User user) {
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
    public List<MemberJson> getMembers(
            @PathVariable Integer projectId,
            @CurrentUser User user) {
        Project project = getProjectOrThrow(projectId);
        throwIfNotMember(user, project);
        List<Member> members = projectService.getMembers(project);
        List<MemberJson> jsons = new ArrayList<>();
        for (var member : members) {
            jsons.add(projectService.memberToJson(member));
        }
        return jsons;
    }

    @GetMapping("/{projectId}/members/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @LoginRequired
    public MemberJson getMember(
            @PathVariable Integer projectId,
            @PathVariable Integer userId,
            @CurrentUser User user) {
        Project project = getProjectOrThrow(projectId);
        throwIfNotMember(user, project);
        Member member = projectService.getMember(project, userId);
        if (member == null) {
            throw new BusinessException(BusinessErrorType.MEMBER_NOT_FOUND,
                    "User with id " + userId + " is not the member of project " + projectId);
        }
        return projectService.memberToJson(projectService.getMember(project, userId));
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
        if (!projectService.isMemberOf(opUser, project)) {
            throw new BusinessException(BusinessErrorType.MEMBER_NOT_FOUND,
                    "User with id " + userId + " is not the member of project " + projectId);
        }
        if (memberJson.getRole().equals("SuperAdmin")) {
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
        if (projectService.isSuperAdminOf(opUser, project)) {
            throw new BusinessException(BusinessErrorType.PERMISSION_DENIED,
                    "Can't kick the super admin of the project. Super admins must transfer or delete the project before quitting.");
        } else if (projectService.isAdminOf(opUser, project)) {
            throwIfNotSuperAdmin(user, project);
        } else if (projectService.isMemberOf(opUser, project)) {
            throwIfNotAdmin(user, project);
        } else {
            throw new BusinessException(BusinessErrorType.MEMBER_NOT_FOUND,
                    "User with id " + userId + "is not the member of project " + projectId);
        }
        projectService.deleteMember(opUser, project);
    }

    @GetMapping("{projectId}/tasks")
    @ResponseStatus(HttpStatus.OK)
    @LoginRequired
    public List<TaskJson> getTasks(
            @PathVariable Integer projectId,
            @CurrentUser User user) {
        Project project = getProjectOrThrow(projectId);
        throwIfNotMember(user, project);
        List<Task> tasks = projectService.getProjectTasks(projectId);
        List<TaskJson> jsons = new ArrayList<>();
        tasks.forEach(t -> jsons.add(projectService.taskToJson(t)));
        return jsons;
    }

    @PostMapping("{projectId}/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    @LoginRequired
    public TaskJson createTask(
            @PathVariable Integer projectId,
            @RequestBody TaskJson taskJson,
            @CurrentUser User user) {
        Project project = getProjectOrThrow(projectId);
        throwIfNotAdmin(user, project);
        Task task = new Task();
        task.setBody(taskJson.getBody());
        task.setComplete(taskJson.isComplete());
        task.setCompleteAt(taskJson.getCompleteAt());
        task.setCompleterId(taskJson.getCompleter().getId());
        task.setCreateAt(LocalDateTime.now());
        task.setCreatorId(user.getId());
        task.setId(taskJson.getId());
        task.setProjectId(projectId);
        task.setTitle(taskJson.getTitle());
        task = projectService.createTask(task);
        taskJson.getExecutors().forEach(u -> projectService.addExecutor(taskJson.getId(), u.getId()));
        return projectService.taskToJson(task);
    }

    @GetMapping("{projectId}/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    @LoginRequired
    public TaskJson getTask(
            @PathVariable Integer projectId,
            @PathVariable Integer id,
            @CurrentUser User user) {
        Project project = getProjectOrThrow(projectId);
        throwIfNotMember(user, project);
        Task task = getTaskOrThrow(id, projectId);
        return projectService.taskToJson(task);
    }

    @PutMapping("{projectId}/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    @LoginRequired
    public void updateTask(
            @PathVariable Integer projectId,
            @PathVariable Integer id,
            @RequestBody TaskJson json,
            @CurrentUser User user) {
        Project project = getProjectOrThrow(projectId);
        throwIfNotAdmin(user, project);
        Task task = getTaskOrThrow(id, projectId);
        task.setTitle(json.getTitle());
        task.setBody(json.getBody());
        projectService.updateTask(task);
        List<User> oldExecutors = projectService.getExecutors(task.getId());
        for (User u : oldExecutors) {
            if (json.getExecutors().stream().noneMatch(u2 -> u.getId() == u2.getId())) {
                projectService.deleteExecutor(id, u.getId());
            }
        }
        for (UserJson u : json.getExecutors()) {
            if (oldExecutors.stream().noneMatch(u2 -> u.getId() == u2.getId())) {
                projectService.addExecutor(id, u.getId());
            }
        }
    }

    @PatchMapping("{projectId}/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    @LoginRequired
    public void updateTaskState(
            @PathVariable Integer projectId,
            @PathVariable Integer id,
            @RequestBody TaskJson json,
            @CurrentUser User user) {
        Project project = getProjectOrThrow(projectId);
        throwIfNotMember(user, project);
        Task task = getTaskOrThrow(id, projectId);
        task.setComplete(json.isComplete());
        task.setCompleterId(user.getId());
        task.setCompleteAt(LocalDateTime.now());
        projectService.updateTask(task);
    }

    @DeleteMapping("{projectId}/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    @LoginRequired
    public void deleteTask(
            @PathVariable Integer projectId,
            @PathVariable Integer id,
            @CurrentUser User user) {
        Project project = getProjectOrThrow(projectId);
        throwIfNotAdmin(user, project);
        projectService.deleteTask(id);
    }

    /*@GetMapping("/{projectId}/tasks/{taskId}/comments")
    @ResponseStatus(HttpStatus.OK)
    @LoginRequired
    public List<CommentJson> getComments(
            @PathVariable Integer projectId,
            @PathVariable Integer taskId,
            @CurrentUser User user){
        Project project = getProjectOrThrow(projectId);
        throwIfNotMember(user, project);
        getTaskOrThrow(projectId, taskId);
        return projectService.getTaskCommentJsons(taskId);
    }

    @PostMapping("/{projectId}/tasks/{taskId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    @LoginRequired
    public CommentJson createComment(
            @PathVariable Integer projectId,
            @PathVariable Integer taskId,
            @RequestBody CommentJson json,
            @CurrentUser User user) {
        Project project = getProjectOrThrow(projectId);
        throwIfNotMember(user, project);
        getTaskOrThrow(projectId, taskId);
        Comment comment = new Comment();
        comment.setBody(json.getBody());
        comment.setCreateAt(json.getCreateAt());
        comment.setTaskId(taskId);
        comment.setUserId(user.getId());
        return projectService.saveComment(comment);
    }

    @GetMapping("/{projectId}/tasks/{taskId}/comments/{id}")
    @ResponseStatus(HttpStatus.OK)
    @LoginRequired
    public CommentJson getComment(
            @PathVariable Integer projectId,
            @PathVariable Integer taskId,
            @PathVariable Integer id,
            @CurrentUser User user){
        Project project = getProjectOrThrow(projectId);
        throwIfNotMember(user, project);
        getTaskOrThrow(projectId, taskId);
        return getCommentJsonOrThrow(id, taskId);
    }

    @DeleteMapping("/{projectId}/tasks/{taskId}/comments/{id}")
    @ResponseStatus(HttpStatus.OK)
    @LoginRequired
    public void deleteComment(
            @PathVariable Integer projectId,
            @PathVariable Integer taskId,
            @PathVariable Integer id,
            @CurrentUser User user){
        Project project = getProjectOrThrow(projectId);
        throwIfNotAdmin(user, project);
        getTaskOrThrow(projectId, taskId);
        getCommentJsonOrThrow(id, taskId);
        projectService.deleteComment(id);
    }*/

    /*@PostMapping("/{projectId}/invitations")
    @ResponseStatus(HttpStatus.CREATED)
    @LoginRequired
    public InvitationJson sendInvitation(
            @PathVariable Integer projectId,
            @RequestBody InvitationJson json,
            @CurrentUser User user){
        Project project = getProjectOrThrow(projectId);
        throwIfNotAdmin(user, project);
        User receiver = getUserOrThrow(json.getReceiver().getId());
        throwIfMember(receiver, project);
        if(projectService.getInvitationByReceiver(project, receiver) != null){
            throw new BusinessException(BusinessErrorType.INVITATION_ALREADY_EXIST,
                    "User " + user.getNickname() + " has already received an invitation to this project.");
        }
        Invitation invitation = projectService.sendInvitation(user, receiver, project);
        return projectService.invitationToJson(invitation);
    }

    @GetMapping("/{projectId}/invitations")
    @ResponseStatus(HttpStatus.OK)
    @LoginRequired
    public List<InvitationJson> getInvitations(
            @PathVariable Integer projectId,
            @CurrentUser User user){
        Project project = getProjectOrThrow(projectId);
        throwIfNotAdmin(user, project);
        List<Invitation> invitations = projectService.getInvitations(project);
        List<InvitationJson> jsons = new ArrayList<>();
        invitations.forEach(i -> jsons.add(projectService.invitationToJson(i)));
        return jsons;
    }

    @GetMapping("/{projectId}/invitations/{id}/cancel")
    @ResponseStatus(HttpStatus.OK)
    @LoginRequired
    public void cancelInvitation(
            @PathVariable Integer projectId,
            @PathVariable Integer id,
            @CurrentUser User user){
        Project project = getProjectOrThrow(projectId);
        Invitation invitation = getInvitationOrThrow(id, projectId);
        projectService.cancelInvitation(user, invitation);
    }

    @GetMapping("/{projectId}/invitations/{id}/accept")
    @ResponseStatus(HttpStatus.OK)
    @LoginRequired
    public void acceptInvitation(
            @PathVariable Integer projectId,
            @PathVariable Integer id,
            @CurrentUser User user){
        Project project = getProjectOrThrow(projectId);
        throwIfMember(user, project);
        Invitation invitation = getInvitationOrThrow(id, projectId);
        throwIfNotEqual(user, invitation.getReceiverId());
        projectService.acceptInvitation(user, invitation);
    }

    @GetMapping("/{projectId}/invitations/{id}/reject")
    @ResponseStatus(HttpStatus.OK)
    @LoginRequired
    public void rejectInvitation(
            @PathVariable Integer projectId,
            @PathVariable Integer id,
            @CurrentUser User user){
        Project project = getProjectOrThrow(projectId);
        throwIfMember(user, project);
        Invitation invitation = getInvitationOrThrow(id, projectId);
        throwIfNotEqual(user, invitation.getReceiverId());
        projectService.rejectInvitation(user, invitation);
    }*/

    private void throwIfNotMember(User user, Project project) {
        if (!projectService.isMemberOf(user, project)) {
            throw new BusinessException(BusinessErrorType.PERMISSION_DENIED,
                    "User " + user.getNickname() + " is not the member of " + project.getName() + ".");
        }
    }

    private void throwIfMember(User user, Project project) {
        if (projectService.isMemberOf(user, project)) {
            throw new BusinessException(BusinessErrorType.RECEIVER_ALREADY_IN_PROJECT,
                    "User " + user.getNickname() + " is already the member of " + project.getName() + ".");
        }
    }

    private void throwIfNotEqual(User currentUser, int expectedUserId) {
        if (currentUser.getId() != expectedUserId) {
            throw new BusinessException(BusinessErrorType.PERMISSION_DENIED,
                    "User " + currentUser + " can not do the private operation of other users.");
        }
    }

    private void throwIfNotAdmin(User user, Project project) {
        if (!projectService.isAdminOf(user, project)) {
            throw new BusinessException(BusinessErrorType.PERMISSION_DENIED,
                    "User " + user.getNickname() + " is not the admin of " + project.getName() + ".");
        }
    }

    private void throwIfNotSuperAdmin(User user, Project project) {
        if (!projectService.isSuperAdminOf(user, project)) {
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

    private Task getTaskOrThrow(int taskId, int projectId) {
        Task task = projectService.getTaskById(taskId, projectId);
        if (task == null) {
            throw new BusinessException(BusinessErrorType.USER_NOT_FOUND,
                    "Task with id " + taskId + " in project with id " + projectId + " not found.");
        }
        return task;
    }

    private Invitation getInvitationOrThrow(int invitationId, int projectId) {
        Invitation invitation = projectService.getInvitationById(invitationId);
        if (invitation == null || invitation.getProjectId() != projectId) {
            throw new BusinessException(BusinessErrorType.USER_NOT_FOUND,
                    "Invitation with id " + invitationId + " in project with id " + projectId + " not found.");
        }
        return invitation;
    }

    private CommentJson getCommentJsonOrThrow(int commentId, int taskId) {
        CommentJson json = projectService.getTaskCommentJson(taskId, commentId);
        if (json == null) {
            throw new BusinessException(BusinessErrorType.USER_NOT_FOUND,
                    "Comment with id " + commentId + " in task with id " + taskId + " not found.");
        }
        return json;
    }
}

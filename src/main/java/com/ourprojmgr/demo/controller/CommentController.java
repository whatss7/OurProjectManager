package com.ourprojmgr.demo.controller;

import com.ourprojmgr.demo.controller.utility.CurrentUser;
import com.ourprojmgr.demo.controller.utility.LoginRequired;
import com.ourprojmgr.demo.dbmodel.Comment;
import com.ourprojmgr.demo.dbmodel.Project;
import com.ourprojmgr.demo.dbmodel.Task;
import com.ourprojmgr.demo.dbmodel.User;
import com.ourprojmgr.demo.exception.BusinessErrorType;
import com.ourprojmgr.demo.exception.BusinessException;
import com.ourprojmgr.demo.jsonmodel.CommentJson;
import com.ourprojmgr.demo.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 处理与评论有关的请求
 *
 * @author 董晓艺
 */
@RestController
@RequestMapping("/api/projects/{projectId}/tasks/{taskId}/comments")
public class CommentController {

    private final IProjectService projectService;

    @Autowired
    public CommentController(IProjectService projectService) {
        this.projectService = projectService;
    }

    /**
     * 获取某项任务下的评论
     * @param user 当前用户
     * @param projectId 项目id
     * @param taskId 任务id
     * @return List<CommentJson> 某项任务下的所有评论
     * @throws BusinessException 业务异常
     */
    @GetMapping
    @LoginRequired
    @ResponseStatus(HttpStatus.OK)
    public List<CommentJson> getTaskCommentJsons(@CurrentUser User user,
                                                 @PathVariable int projectId,
                                                 @PathVariable int taskId){
        Project project = getProjectOrThrow(projectId);  //项目不存在则抛异常
        checkMemberOrThrow(user, project); //不是本项目的Member则抛异常
        getTaskOrThrow(taskId,projectId); //任务不存在则抛异常
        return projectService.getTaskCommentJsons(taskId);
    }

    /**
     * 获取某项任务下的某条评论
     * @param user 当前用户
     * @param projectId 项目id
     * @param taskId 任务id
     * @param id 评论id
     * @return CommentJson 评论的Json
     * @throws BusinessException 业务异常
     */
    @GetMapping("/{id}")
    @LoginRequired
    @ResponseStatus(HttpStatus.OK)
    public CommentJson getTaskCommentJson(@CurrentUser User user,
                                          @PathVariable int projectId,
                                          @PathVariable int taskId,
                                          @PathVariable int id){
        Project project = getProjectOrThrow(projectId); //项目不存在则抛异常
        checkMemberOrThrow(user, project); //不是本项目的Member则抛异常
        getTaskOrThrow(taskId,projectId);  //任务不存在则抛异常
        CommentJson commentJson = projectService.getTaskCommentJson(taskId, id);
        if(commentJson == null){ //评论不存在则抛异常
            throw new BusinessException(BusinessErrorType.COMMENT_NOT_FOUND, "Comment with id '" + id + "' not found");
        }
        return commentJson;
    }

    /**
     * 在某项任务下发评论
     * @param user 当前用户
     * @param projectId 项目id
     * @param taskId 任务id
     * @param commentJson 请求体中的Json
     * @return CommentJson 评论的Json
     * @throws BusinessException 业务异常
     */
    @PostMapping
    @LoginRequired
    @ResponseStatus(HttpStatus.CREATED)
    public CommentJson saveComment(@CurrentUser User user,
                                   @PathVariable int projectId,
                                   @PathVariable int taskId,
                                   @RequestBody CommentJson commentJson){
        Project project = getProjectOrThrow(projectId); //项目不存在则抛异常
        checkMemberOrThrow(user, project); //不是本项目的Member则抛异常
        getTaskOrThrow(taskId,projectId);  //任务不存在则抛异常
        Comment comment = new Comment();
        comment.setBody(commentJson.getBody());
        comment.setCreateAt(LocalDateTime.now());
        comment.setTaskId(taskId);
        comment.setUserId(user.getId());
        return projectService.saveComment(comment);
    }

    /**
     * 删除某条评论
     * @param user 当前用户
     * @param projectId 项目id
     * @param taskId 任务id
     * @param id 评论id
     * @throws BusinessException 业务异常
     */
    @DeleteMapping("/{id}")
    @LoginRequired
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@CurrentUser User user,
                              @PathVariable int projectId,
                              @PathVariable int taskId,
                              @PathVariable int id){
        Project project = getProjectOrThrow(projectId);  //项目不存在则抛异常
        checkAdminOrThrow(user, project); //不是本项目的Member则抛异常
        getTaskOrThrow(taskId,projectId);  //任务不存在则抛异常
        CommentJson commentJson = projectService.getTaskCommentJson(taskId, id);
        if(commentJson == null){  //评论不存在则抛异常
            throw new BusinessException(BusinessErrorType.COMMENT_NOT_FOUND, "Comment with id '" + id + "' not found");
        }
        projectService.deleteComment(id);
    }

    // ---------------------- 以下为辅助方法 ----------------------

    /**
     * 根据id获取项目，若项目不存在则抛异常
     * @param projectId 项目id
     * @return Project 项目
     * @throws BusinessException 未找到项目
     */
    private Project getProjectOrThrow(int projectId){
        Project project = projectService.getProjectById(projectId);
        if(project == null){
            throw new BusinessException(BusinessErrorType.PROJECT_NOT_FOUND, "project '" + projectId + "' not exist");
        }
        return project;
    }

    /**
     * 根据项目id和任务id获取任务，若任务不存在或任务不属于项目则抛异常
     * @param taskId 任务id
     * @param projectId 项目id
     * @return Task 任务
     * @throws BusinessException 业务异常
     */
    private Task getTaskOrThrow(int taskId, int projectId){
        Task task = projectService.getTaskById(taskId, projectId);
        if(task == null){  //任务不存在则抛异常
            throw new BusinessException(BusinessErrorType.TASK_NOT_FOUND, "task '" + taskId + "' not exist");
        }
        if(task.getProjectId() != projectId){ //任务不属于项目则抛异常
            throw new BusinessException(BusinessErrorType.TASK_NOT_FOUND,
                    "task '" + taskId + "' not exist in project '" + projectId + "'");
        }
        return task;
    }

    /**
     * 检查当前用户是否是项目的Member，若不是则抛异常
     * @param user 当前用户
     * @param project 项目
     * @throws BusinessException 不是Member
     */
    private void checkMemberOrThrow(User user, Project project){
        if(!projectService.isMemberOf(user, project)){
            throw new BusinessException(BusinessErrorType.PERMISSION_DENIED, "Not Member");
        }
    }

    /**
     * 检查当前用户是否是项目的Admin，若不是则抛异常
     * @param user 当前用户
     * @param project 项目
     * @throws BusinessException 不是Admin
     */
    private void checkAdminOrThrow(User user, Project project){
        if(!projectService.isAdminOf(user, project)){
            throw new BusinessException(BusinessErrorType.PERMISSION_DENIED, "Not Admin");
        }
    }
}

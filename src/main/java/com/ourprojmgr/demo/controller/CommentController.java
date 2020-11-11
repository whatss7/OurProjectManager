package com.ourprojmgr.demo.controller;

import com.ourprojmgr.demo.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

package com.ourprojmgr.demo.controller;

import com.ourprojmgr.demo.controller.utility.LoginRequired;
import com.ourprojmgr.demo.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * 负责处理用户相关请求的控制器
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    //TODO di
    private IUserService userService;

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }


    /**
     * 登出
     *
     * @author 朱华彬
     */
    @GetMapping("/logout")
    @LoginRequired
    public ResponseEntity<?> logout() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

package com.ourprojmgr.demo.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ourprojmgr.demo.controller.utility.CurrentUser;
import com.ourprojmgr.demo.controller.utility.LoginRequired;
import com.ourprojmgr.demo.dbmodel.User;
import com.ourprojmgr.demo.exception.BusinessErrorType;
import com.ourprojmgr.demo.exception.BusinessException;
import com.ourprojmgr.demo.jsonmodel.LoginJson;
import com.ourprojmgr.demo.jsonmodel.UserJson;
import com.ourprojmgr.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 处理用户认证相关请求
 */
@RestController
@RequestMapping("/api")
public class UserAuthController {

    private final IUserService userService;

    @Autowired
    public UserAuthController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * 登录，若用户名和密码正确则返回用户的 token，反之则抛异常
     *
     * @param loginJson 登录信息
     * @return 根据用户生成的token
     * @throws BusinessException 错误的用户名或密码
     * @author 董晓艺
     */
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody LoginJson loginJson) {
        User user = userService.getUserByName(loginJson.getUsername());
        if (user == null) {  //用户名错误
            throw new BusinessException(BusinessErrorType.WRONG_PASSWORD_OR_USERNAME,
                    "username '" + loginJson.getUsername() + "' not exist");
        }
        if (!userService.isRightPassword(user, loginJson.getPassword())) {  //密码错误
            throw new BusinessException(BusinessErrorType.WRONG_PASSWORD_OR_USERNAME, "wrong password");
        }
        return getTokenByUser(user);
    }

    /**
     * 登出，暂时先这么简单处理吧
     *
     * @author 朱华彬
     */
    @GetMapping("/logout")
    @LoginRequired
    public ResponseEntity<Void> logout() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 通过 token 获取当前用户
     *
     * @param currentUser 当前用户
     * @return 当前用户的 JSON
     * @author 朱华彬
     */
    @GetMapping("/whoami")
    @ResponseStatus(HttpStatus.OK)
    @LoginRequired
    public UserJson whoami(@CurrentUser User currentUser) {
        return userService.userToJson(currentUser);
    }

    /**
     * 根据用户生成 token，token 中保存了用户 Id
     *
     * @param user 用户
     * @return 生成的 token
     * @author 董晓艺
     */
    private static String getTokenByUser(User user) {
        return JWT.create()
                .withAudience(Integer.toString(user.getId()))
                .sign(Algorithm.HMAC256(user.getHashedPassword()));
    }

}

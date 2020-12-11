package com.ourprojmgr.demo.controller;

import com.ourprojmgr.demo.controller.utility.CurrentUser;
import com.ourprojmgr.demo.controller.utility.LoginRequired;
import com.ourprojmgr.demo.dbmodel.Notification;
import com.ourprojmgr.demo.dbmodel.User;
import com.ourprojmgr.demo.exception.BusinessErrorType;
import com.ourprojmgr.demo.exception.BusinessException;
import com.ourprojmgr.demo.jsonmodel.*;
import com.ourprojmgr.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


/**
 * 处理用户有关的请求
 *
 * @author 董晓艺
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * 注册，将用户信息保存到数据库中，若注册的用户名已存在则抛异常
     *
     * @param signUpJson 注册信息
     * @throws BusinessException 用户已存在
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void signup(@RequestBody SignUpJson signUpJson) {
        User user = userService.getUserByName(signUpJson.getUsername());
        if (user != null) {  //用户名已存在
            throw new BusinessException(BusinessErrorType.USER_ALREADY_EXIST, "user '" + signUpJson.getUsername() + "' already exist");
        }
        User newUser = new User();
        newUser.setUsername(signUpJson.getUsername());
        newUser.setNickname(signUpJson.getNickname());
        userService.hashPasswordAndSet(newUser, signUpJson.getPassword()); //生成并设置用户的盐和哈希密码
        newUser.setCreateAt(LocalDateTime.now());
        newUser.setUpdateAt(LocalDateTime.now());
        userService.saveUser(newUser);
    }

    /**
     * 获取用户信息，若用户不存在则抛异常
     *
     * @param username 用户名
     * @return 用户Json
     * @throws BusinessException 未找到用户
     */
    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public UserJson getUserJson(@PathVariable String username) {
        User user = getUserFromNameOrThrow(username);
        return userService.userToJson(user);
    }

    /**
     * 修改用户名和昵称，若操作者不是对应的用户（即操作者企图修改其他用户的信息）或新用户名已存在则抛异常
     *
     * @param username    要修改信息的用户的用户名
     * @param userJson    修改信息
     * @param currentUser 当前用户
     * @throws BusinessException 业务异常
     */
    @PutMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    @LoginRequired
    public void updateUser(@PathVariable String username,
                           @RequestBody UserJson userJson,
                           @CurrentUser User currentUser) {
        checkSameUserOrThrow(username, currentUser); //检查操作者与要修改的用户是否为同一用户，若不是则抛异常
        User user = getUserFromNameOrThrow(username);
        //若新用户名已存在则抛异常
        if (!userJson.getUsername().equals(username) && userService.getUserByName(userJson.getUsername()) != null) {
            throw new BusinessException(BusinessErrorType.USER_ALREADY_EXIST, "user '" + userJson.getUsername() + "' already exist");
        }
        userService.updateUsernameAndNickname(user, userJson.getUsername(), userJson.getNickname());
    }

    /**
     * 更新密码，若操作者与要更新密码的用户不是同一用户或旧密码错误则抛异常
     *
     * @param username           要更新密码的用户的用户名
     * @param updatePasswordJson 更新密码信息
     * @param currentUser        当前用户
     * @throws BusinessException 业务异常
     */
    @PutMapping("/{username}/password")
    @ResponseStatus(HttpStatus.OK)
    @LoginRequired
    public void updatePassword(@PathVariable String username,
                               @RequestBody UpdatePasswordJson updatePasswordJson,
                               @CurrentUser User currentUser) {
        checkSameUserOrThrow(username, currentUser); //检查操作者与要更新密码的用户是否是同一用户，若不是则抛异常
        User user = getUserFromNameOrThrow(username);
        //更新密码，若旧密码错误则抛异常
        userService.updatePassword(user, updatePasswordJson.getOldPassword(), updatePasswordJson.getNewPassword());
    }

    /**
     * 删除用户，若操作者不是对应的用户则抛异常
     *
     * @param username    要删除的用户的用户名
     * @param currentUser 当前用户
     * @throws BusinessException 业务异常
     */
    @DeleteMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    @LoginRequired
    public void deleteUser(@PathVariable String username, @CurrentUser User currentUser) {
        checkSameUserOrThrow(username, currentUser);  //检查操作者是否是要删除的用户，若不是则抛异常
        User user = getUserFromNameOrThrow(username);
        userService.deleteUser(user);
    }

    /**
     * 获取用户参加的项目，若操作者不是对应的用户则抛异常
     *
     * @param username    要获取项目的用户名
     * @param currentUser 当前用户
     * @return 用户参加的所有项目
     * @throws BusinessException 业务异常
     */
    @GetMapping("/{username}/projects")
    @ResponseStatus(HttpStatus.OK)
    @LoginRequired
    public List<ProjectJson> getUserProjects(@PathVariable String username, @CurrentUser User currentUser) {
        checkSameUserOrThrow(username, currentUser); //检查操作者与要获取项目的用户是否是同一用户，若不是则抛异常
        User user = getUserFromNameOrThrow(username);
        return userService.getUserProjectJsons(user);
    }

    /**
     * 获取用户参加的某个项目，若操作者不是对应的用户或未找到项目则抛异常
     *
     * @param username    要获取项目的用户名
     * @param pid         项目id
     * @param currentUser 当前用户
     * @return 项目Json
     * @throws BusinessException 业务异常
     */
    @GetMapping("/{username}/projects/{id}")
    @ResponseStatus(HttpStatus.OK)
    @LoginRequired
    public ProjectJson getUserProjectByPid(@PathVariable("username") String username,
                                           @PathVariable("id") Integer pid,
                                           @CurrentUser User currentUser) {
        checkSameUserOrThrow(username, currentUser); //检查操作者与要获取项目的用户是否是同一用户，若不是则抛异常
        User user = getUserFromNameOrThrow(username);
        ProjectJson projectJson = userService.getUserProjectJson(user, pid);
        if (projectJson == null) { //未找到项目则抛异常
            throw new BusinessException(BusinessErrorType.PROJECT_NOT_FOUND,
                    "user '" + username + "' does not participate in project '" + pid + "'");
        }
        return projectJson;
    }

    /**
     * 获取用户收到的通知，若操作者不是对应的用户则抛异常
     *
     * @param username    要获取通知的用户名
     * @param currentUser 当前用户
     * @return 用户收到的所有通知
     * @throws BusinessException 业务异常
     */
    @GetMapping("/{username}/notifications/recv")
    @ResponseStatus(HttpStatus.OK)
    @LoginRequired
    public List<NotificationJson> getUserRecvNotifications(@PathVariable String username,
                                                           @CurrentUser User currentUser) {
        //检查操作者与要获取通知的用户是否是同一用户，若不是则抛异常
        checkSameUserOrThrow(username, currentUser);
        User user = getUserFromNameOrThrow(username);
        return userService.getUserRecvNotificationJsons(user);
    }

    /**
     * 获取用户收到或发送的某条通知，若操作者不是对应的用户或未找到通知则抛异常
     *
     * @param username    要获取通知的用户名
     * @param nid         通知id
     * @param currentUser 当前用户
     * @return 通知Json
     * @throws BusinessException 业务异常
     */
    @GetMapping("/{username}/notifications/{id}")
    @ResponseStatus(HttpStatus.OK)
    @LoginRequired
    public NotificationJson getUserNotificationByNid(@PathVariable("username") String username,
                                                     @PathVariable("id") Integer nid,
                                                     @CurrentUser User currentUser) {
        //检查操作者与要获取通知的用户是否是同一用户，若不是则抛异常
        checkSameUserOrThrow(username, currentUser);
        NotificationJson notificationJson = userService.getUserRecvNotificationJson(currentUser, nid);
        if (notificationJson == null) {
            notificationJson = userService.getUserSendNotificationJson(currentUser, nid);
        }
        if (notificationJson == null) { //若未找到通知则抛异常
            throw new BusinessException(BusinessErrorType.NOTIFICATION_NOT_FOUND,
                    "user '" + username + "' has not received notification '" + nid + "'");
        }
        if (currentUser.getId() != notificationJson.getSender().getId()
                && currentUser.getId() != notificationJson.getReceiver().getId()) {
            //该用户既不是发送者也不是接收者
            throw new BusinessException(
                    BusinessErrorType.PERMISSION_DENIED,
                    "user " + username + " is not sender or receiver of this notification.");
        }
        return notificationJson;
    }

    /**
     * 修改通知已读状态，若操作者不是对应的用户或未找到通知则抛异常
     *
     * @param username        要修改通知已读状态的用户的用户名
     * @param nid             要修改状态的通知的id
     * @param newNotification 新通知，只使用read字段
     * @param currentUser     当前用户
     * @throws BusinessException 业务异常
     */
    @PatchMapping("/{username}/notifications/{id}")
    @ResponseStatus(HttpStatus.OK)
    @LoginRequired
    public void patchUserRecvNotificationByNid(@PathVariable("username") String username,
                                               @PathVariable("id") Integer nid,
                                               @RequestBody NotificationJson newNotification,
                                               @CurrentUser User currentUser) {
        //检查操作者与要修改通知状态的用户是否是同一用户，若不是则抛异常
        checkSameUserOrThrow(username, currentUser);
        Notification notification = userService.getUserRecvNotification(currentUser, nid);
        if (notification == null) { //若未找到通知则抛异常
            throw new BusinessException(BusinessErrorType.NOTIFICATION_NOT_FOUND,
                    "user '" + username + "' has not received notification '" + nid + "'");
        }
        notification.setRead(newNotification.isRead());
        userService.updateNotification(notification);
    }

    /**
     * 获取用户发送的所有通知，若操作者不是对应的用户则抛异常
     *
     * @param username    要获取通知的用户的用户名
     * @param currentUser 当前用户
     * @return 用户发送的所有通知
     * @throws BusinessException 业务异常
     */
    @GetMapping("/{username}/notifications/send")
    @ResponseStatus(HttpStatus.OK)
    @LoginRequired
    public List<NotificationJson> getUserSendNotifications(@PathVariable String username,
                                                           @CurrentUser User currentUser) {
        checkSameUserOrThrow(username, currentUser);  //检查操作者与要获取通知的用户是否是同一用户，若不是则抛异常
        User user = getUserFromNameOrThrow(username);
        return userService.getUserSendNotificationJsons(user);
    }

    /**
     * 发送通知，若接收的用户不存在则抛异常
     *
     * @param sender           发送者
     * @param receiverUsername 接收者的用户名
     * @param notificationJson 发送的通知
     */
    @PostMapping("/{username}/notifications")
    @ResponseStatus(HttpStatus.CREATED)
    @LoginRequired
    public void sendNotification(@CurrentUser User sender,
                                 @PathVariable("username") String receiverUsername,
                                 @RequestBody NotificationJson notificationJson) {
        User receiver = getUserFromNameOrThrow(receiverUsername); //若接收者不存在则抛异常
        Notification notification = new Notification();
        notification.setBody(notificationJson.getBody());
        notification.setCreateAt(LocalDateTime.now());
        notification.setRead(false);  //通知已读状态初始化为false
        notification.setTitle(notificationJson.getTitle());
        notification.setSenderId(sender.getId());
        notification.setReceiverId(receiver.getId());
        userService.saveNotification(notification);
    }


    // ---------------------- 以下为辅助方法 ----------------------

    /**
     * 根据用户名获取用户，若用户不存在则抛异常
     *
     * @param username 用户名
     * @return User 用户
     * @throws BusinessException 未找到用户
     */
    private User getUserFromNameOrThrow(String username) {
        User user = userService.getUserByName(username);
        if (user == null) {
            throw new BusinessException(BusinessErrorType.USER_NOT_FOUND, "username '" + username + "' not exist");
        }
        return user;
    }

    /**
     * 检查当前用户与用户名为username的用户是否是同一用户，若不是则抛异常
     *
     * @param username    用户名
     * @param currentUser 当前用户
     * @throws BusinessException 权限被拒绝
     */
    private void checkSameUserOrThrow(String username, User currentUser) {
        if (!currentUser.getUsername().equals(username)) {
            throw new BusinessException(BusinessErrorType.PERMISSION_DENIED, "attempt to operate another user");
        }
    }
}

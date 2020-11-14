package com.ourprojmgr.demo.service;

import com.ourprojmgr.demo.dbmodel.Notification;
import com.ourprojmgr.demo.dbmodel.User;
import com.ourprojmgr.demo.jsonmodel.NotificationJson;
import com.ourprojmgr.demo.jsonmodel.ProjectJson;
import com.ourprojmgr.demo.jsonmodel.UserJson;

import java.util.List;

/**
 * 与用户有关的业务逻辑
 */
public interface IUserService {

    /**
     * 按照 ID 获取用户
     *
     * @param id 用户 ID
     * @return User 实体类
     */
    User getUserById(int id);

    /**
     * 按照用户名获取用户
     *
     * @param username 用户名
     * @return User 实体类
     */
    User getUserByName(String username);

    /**
     * 将 DB Model 的 User 转换为 JSON Model
     */
    UserJson userToJson(User user);

    /**
     * 更新密码
     *
     * @param user        用户
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @throws com.ourprojmgr.demo.exception.BusinessException 若旧密码不正确，则抛异常
     */
    void updatePassword(User user, String oldPassword, String newPassword);

    /**
     * 更新用户名和昵称。
     * <p/>
     * 注：newUsername 和 newNickname 可以与原来的相同。
     *
     * @param user        用户
     * @param newUsername 新用户名
     * @param newNickname 新昵称
     * @throws com.ourprojmgr.demo.exception.BusinessException 若 newUsername 已被他人使用，则抛异常
     */
    void updateUsernameAndNickname(User user, String newUsername, String newNickname);

    /**
     * 获取该用户参加项目的数量
     *
     * @param user 用户
     */
    int getProjectCount(User user);

    /**
     * 获取该用户参加的项目
     *
     * @param user 用户
     */
    List<ProjectJson> getUserProjectJsons(User user);

    /**
     * 保存用户
     *
     * @param user 用户
     */
    void saveUser(User user);

    /**
     * 生成并设置用户的盐值，再根据盐值生成并设置用户的哈希密码
     * @param user 用户
     * @param password 原始密码
     */
    void hashPasswordAndSet(User user, String password);

    /**
     * 检查原始密码是否正确
     * @param user 用户
     * @param password 原始密码
     * @return
     */
    boolean isRightPassword(User user, String password);

    /**
     * 删除用户
     * @param user 用户
     */
    void deleteUser(User user);

    /**
     * 获取该用户参与的某个项目
     * @param user 用户
     * @param projectId 项目id
     * @return ProjectJson 项目Json
     */
    ProjectJson getUserProjectJson(User user, Integer projectId);

    /**
     * 获取该用户收到的通知
     * @param user 用户
     * @return List<NotificationJson> 用户收到的所有通知
     */
    List<NotificationJson> getUserRecvNotificationJsons(User user);

    /**
     * 获取该用户收到的某条通知
     * @param user 用户
     * @param notificationId 通知id
     * @return NotificationJson 通知Json
     */
    NotificationJson getUserRecvNotificationJson(User user, Integer notificationId);

    /**
     * 获取该用户发送的通知
     * @param user 用户
     * @return List<NotificationJson> 用户发送的所有通知
     */
    List<NotificationJson> getUserSendNotificationJsons(User user);

    /**
     * 获取该用户发送的某条通知
     * @param user 用户
     * @param notificationId 通知id
     * @return NotificationJson 通知Json
     */
    NotificationJson getUserSendNotificationJson(User user, Integer notificationId);

    /**
     * 获取该用户发送的某条通知
     * @param user 用户
     * @param notificationId 通知id
     * @return Notification 通知
     */
    Notification getUserRecvNotification(User user, Integer notificationId);

    /**
     * 更新通知
     * @param notification 新通知
     */
    void updateNotification(Notification notification);

    /**
     * 保存通知
     * @param notification 新通知
     */
    void saveNotification(Notification notification);

    //请自行添加其他方法
}

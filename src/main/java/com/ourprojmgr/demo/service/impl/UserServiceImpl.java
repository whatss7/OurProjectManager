package com.ourprojmgr.demo.service.impl;

import com.ourprojmgr.demo.dao.IUserDao;
import com.ourprojmgr.demo.dbmodel.Notification;
import com.ourprojmgr.demo.dbmodel.Project;
import com.ourprojmgr.demo.dbmodel.User;
import com.ourprojmgr.demo.exception.BusinessErrorType;
import com.ourprojmgr.demo.exception.BusinessException;
import com.ourprojmgr.demo.jsonmodel.NotificationJson;
import com.ourprojmgr.demo.jsonmodel.ProjectJson;
import com.ourprojmgr.demo.jsonmodel.UserJson;
import com.ourprojmgr.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * IUserService 的实现类
 *
 * @author 董晓艺
 */
@Service
public class UserServiceImpl implements IUserService {

    IUserDao userDao;

    @Autowired
    public UserServiceImpl(IUserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 按照 ID 获取用户
     *
     * @param id 用户 ID
     * @return User 实体类
     */
    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    /**
     * 按照用户名获取用户
     *
     * @param username 用户名
     * @return User 实体类
     */
    @Override
    public User getUserByName(String username) {
        return userDao.getUserByName(username);
    }

    /**
     * 将 DB Model 的 User 转换为 JSON Model
     */
    @Override
    public UserJson userToJson(User user) {
        if (user == null) {
            return null;
        }
        UserJson userJson = new UserJson();
        userJson.setId(user.getId());
        userJson.setUsername(user.getUsername());
        userJson.setNickname(user.getNickname());
        userJson.setCreateAt(user.getCreateAt());
        userJson.setUpdateAt(user.getUpdateAt());
        userJson.setProjectCount(getProjectCount(user));
        return userJson;
    }

    /**
     * 更新密码
     *
     * @param user        用户
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @throws com.ourprojmgr.demo.exception.BusinessException 若旧密码不正确，则抛异常
     */
    @Override
    public void updatePassword(User user, String oldPassword, String newPassword) {
        if (!isRightPassword(user, oldPassword)) {   //旧密码不正确则抛异常
            throw new BusinessException(BusinessErrorType.WRONG_OLD_PASSWORD, "wrong old password");
        }
        hashPasswordAndSet(user, newPassword);  //生成并设置哈希密码
        user.setUpdateAt(LocalDateTime.now());
        userDao.updateUser(user);
    }

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
    @Override
    public void updateUsernameAndNickname(User user, String newUsername, String newNickname) {
        user.setUsername(newUsername);
        user.setNickname(newNickname);
        user.setUpdateAt(LocalDateTime.now());
        userDao.updateUser(user);
    }

    /**
     * 获取该用户参加项目的数量
     *
     * @param user 用户
     */
    @Override
    public int getProjectCount(User user) {
        return userDao.countProjectByUserId(user.getId());
    }

    /**
     * 获取该用户参加的项目
     *
     * @param user 用户
     */
    @Override
    public List<ProjectJson> getUserProjectJsons(User user) {
        List<Project> projects = userDao.findProjectByUid(user.getId());
        //将Db Model Project 转化为 ProjectJson
        List<ProjectJson> projectJsons = new ArrayList<>();
        for (Project p : projects) {
            projectJsons.add(projectToJson(p));
        }
        return projectJsons;
    }

    /**
     * 保存用户
     *
     * @param user 用户
     */
    @Override
    public void saveUser(User user) {
        userDao.insertUser(user);
    }

    /**
     * 生成并设置用户的盐值，再根据盐值生成并设置用户的哈希密码
     *
     * @param user     用户
     * @param password 原始密码
     */
    @Override
    public void hashPasswordAndSet(User user, String password) {
        user.setSalt(generateRandomSalt()); //生成并设置用户的盐值
        user.setHashedPassword(generateHashedPassword(password, user.getSalt())); //根据盐值生成并设置用户的哈希密码
    }

    /**
     * 检查原始密码是否正确
     *
     * @param user     用户
     * @param password 原始密码
     * @return
     */
    @Override
    public boolean isRightPassword(User user, String password) {
        String hashedPassword = generateHashedPassword(password, user.getSalt());  //生成哈希密码
        return hashedPassword.equals(user.getHashedPassword());
    }

    /**
     * 删除用户
     *
     * @param user 用户
     */
    @Override
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }

    /**
     * 获取该用户参与的某个项目
     *
     * @param user      用户
     * @param projectId 项目id
     * @return ProjectJson 项目Json
     */
    @Override
    public ProjectJson getUserProjectJson(User user, Integer projectId) {
        return projectToJson(userDao.findProjectByUidPid(user.getId(), projectId));
    }

    /**
     * 获取该用户收到的通知
     *
     * @param user 用户
     * @return List<NotificationJson> 用户收到的所有通知
     */
    @Override
    public List<NotificationJson> getUserRecvNotificationJsons(User user) {
        List<Notification> notifications = userDao.findRecvNotifications(user.getId());
        //将Db Model Notification 转化为 NotificationJson
        List<NotificationJson> notificationJsons = new ArrayList<>();
        for (Notification n : notifications) {
            notificationJsons.add(notificationToJson(n));
        }
        return notificationJsons;
    }

    /**
     * 获取该用户收到的某条通知
     *
     * @param user           用户
     * @param notificationId 通知id
     * @return NotificationJson 通知Json
     */
    @Override
    public NotificationJson getUserRecvNotificationJson(User user, Integer notificationId) {
        return notificationToJson(userDao.findRecvNotification(user.getId(), notificationId));
    }

    /**
     * 获取该用户发送的通知
     *
     * @param user 用户
     * @return List<NotificationJson> 用户发送的所有通知
     */
    @Override
    public List<NotificationJson> getUserSendNotificationJsons(User user) {
        List<Notification> notifications = userDao.findSendNotifications(user.getId());
        //将Db Model Notification 转化为 NotificationJson
        List<NotificationJson> notificationJsons = new ArrayList<>();
        for (Notification n : notifications) {
            notificationJsons.add(notificationToJson(n));
        }
        return notificationJsons;
    }

    /**
     * 获取该用户发送的某条通知
     *
     * @param user           用户
     * @param notificationId 通知id
     * @return NotificationJson 通知Json
     */
    @Override
    public NotificationJson getUserSendNotificationJson(User user, Integer notificationId) {
        return notificationToJson(userDao.findSendNotification(user.getId(), notificationId));
    }

    /**
     * 获取该用户发送的某条通知
     *
     * @param user           用户
     * @param notificationId 通知id
     * @return Notification 通知
     */
    @Override
    public Notification getUserRecvNotification(User user, Integer notificationId) {
        return userDao.findRecvNotification(user.getId(), notificationId);
    }

    /**
     * 更新通知
     *
     * @param notification 新通知
     */
    @Override
    public void updateNotification(Notification notification) {
        userDao.updateNotification(notification);
    }

    /**
     * 保存通知
     *
     * @param notification 新通知
     */
    @Override
    public void saveNotification(Notification notification) {
        userDao.insertNotification(notification);
    }

    /**
     * 生成哈希密码
     *
     * @param password 原始密码
     * @param salt     盐
     * @return String 生成的哈希密码
     */
    private String generateHashedPassword(String password, String salt) {
        return DigestUtils.md5DigestAsHex((password + salt).getBytes());
    }

    /**
     * 生成随机盐
     *
     * @return String 生成的随机盐
     */
    private String generateRandomSalt() {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        //生成8个随机字符
        for (int i = 0; i < 8; ++i) {
            int index = random.nextInt(62);
            sb.append(str.charAt(index));
        }
        //两位时间戳与8位随机字符拼接生成随机盐
        return (System.currentTimeMillis() % 100) + sb.toString();
    }

    /**
     * 将Db Model Notification 转化为 Json Notification
     *
     * @param notification Notification类型的通知
     * @return NotificationJson NotificationJson类型的通知
     */
    private NotificationJson notificationToJson(Notification notification) {
        if (notification == null) {
            return null;
        }
        NotificationJson notificationJson = new NotificationJson();
        notificationJson.setId(notification.getId());
        notificationJson.setRead(notification.isRead());
        notificationJson.setBody(notification.getBody());
        notificationJson.setCreateAt(notification.getCreateAt());
        notificationJson.setReceiver(userToJson(getUserById(notification.getReceiverId())));
        notificationJson.setTitle(notification.getTitle());
        notificationJson.setSender(userToJson(getUserById(notification.getSenderId())));
        return notificationJson;
    }

    /**
     * //将Db Model Project 转化为 Json Project
     *
     * @param project Project类型的通知
     * @return ProjectJson ProjectJson类型的通知
     */
    private ProjectJson projectToJson(Project project) {
        if (project == null) {
            return null;
        }
        ProjectJson projectJson = new ProjectJson();
        projectJson.setId(project.getId());
        projectJson.setName(project.getName());
        projectJson.setDescription(project.getDescription());
        projectJson.setCreateAt(project.getCreateAt());
        projectJson.setUpdateAt(project.getUpdateAt());
        projectJson.setSuperAdmin(userToJson(userDao.getSuperAdminByProjectId(project.getId())));
        List<User> admins = userDao.getAdminsByProjectId(project.getId());
        //将 Db Model User 转化为 Json User
        List<UserJson> adminJsons = new ArrayList<>();
        for (User u : admins) {
            adminJsons.add(userToJson(u));
        }
        projectJson.setAdmins(adminJsons);
        return projectJson;
    }
}

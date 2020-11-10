package com.ourprojmgr.demo.service;

import com.ourprojmgr.demo.dbmodel.Project;
import com.ourprojmgr.demo.dbmodel.User;
import com.ourprojmgr.demo.jsonmodel.UserJson;

import java.util.List;

/**
 * 与用户有关的业务逻辑
 */
public interface IUserService {

    /**
     * 按照 ID 获取用户
     * @param id 用户 ID
     * @return User 实体类
     */
    User getUserById(int id);

    /**
     * 按照用户名获取用户
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
     * TODO 在方法中校验旧密码是否正确
     * @param user 用户
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    void updatePassword(User user, String oldPassword, String newPassword);

    /**
     * 更新用户名和昵称。
     * 新用户名和新昵称可以与原来的相同。
     * TODO 在方法中校验新用户名是否已被别人使用
     * @param user 用户
     * @param newUsername 新用户名
     * @param newNickname 新昵称
     */
    void updateUsernameAndNickname(User user, String newUsername, String newNickname);

    /**
     * 获取该用户参加项目的数量
     * @param user 用户
     */
    int getProjectCount(User user);

    /**
     * 获取该用户参加的项目
     * @param user 用户
     */
    List<Project> getUserProjects(User user);

    //请自行添加其他方法
}

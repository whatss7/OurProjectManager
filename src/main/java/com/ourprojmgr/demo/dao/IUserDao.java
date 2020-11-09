package com.ourprojmgr.demo.dao;

import com.ourprojmgr.demo.dbmodel.User;

public interface IUserDao {
    /**
     * 根据 ID 查询用户
     * @param id 用户 ID
     * @return 若不存在则返回 null
     */
    User findUserById(int id);

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 若不存在则返回 null
     */
    User findUserByName(int username);

    /**
     * 更新用户信息
     */
    User updateUser(User user);

    /**
     * 删除用户
     */
    User deleteUser(User user);

    /**
     * 插入用户
     */
    User insertUser(User user);
}

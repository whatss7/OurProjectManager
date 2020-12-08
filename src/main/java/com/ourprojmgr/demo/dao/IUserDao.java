package com.ourprojmgr.demo.dao;

import com.ourprojmgr.demo.dbmodel.Notification;
import com.ourprojmgr.demo.dbmodel.Project;
import com.ourprojmgr.demo.dbmodel.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IUserDao {
    /**
     * 根据 ID 查询用户
     *
     * @param id 用户 ID
     * @return 若不存在则返回 null
     */
    @Select("select * from User where id = #{id}")
    User getUserById(int id);

    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 若不存在则返回 null
     */
    @Select("select * from User where username = #{username}")
    User getUserByName(String username);

    /**
     * 更新用户信息
     */
    @Update("update User set username = #{username}, nickname = #{nickname}, hashedPassword = #{hashedPassword}, salt = #{salt}, createAt = #{createAt}, updateAt = #{updateAt} where id = #{id}")
    void updateUser(User user);

    /**
     * 删除用户
     */
    @Delete("delete from User where id = #{id}")
    void deleteUser(User user);

    /**
     * 插入用户
     */
    @Insert("insert into User(username, nickname, hashedPassword, salt, createAt, updateAt) values(#{username}, #{nickname}, #{hashedPassword}, #{salt}, #{createAt}, #{updateAt})")
    void insertUser(User user);

    /**
     * 获取用户参加的项目的数量
     *
     * @param userId 用户id
     * @return 用户参加的项目的数量
     */
    @Select("select count(*) from Member m where m.userId = #{userId}")
    Integer countProjectByUserId(Integer userId);

    /**
     * 获取用户参加的项目
     *
     * @param userId 用户id
     * @return 用户参加的所有项目
     */
    @Select("select p.* from Member m, Project p where m.userId = #{userId} and p.id = m.projectId")
    List<Project> findProjectByUid(Integer userId);

    /**
     * 获取用户参加的某个项目
     *
     * @param userId    用户id
     * @param projectId 项目id
     * @return 若不存在则返回null
     */
    @Select("select p.* from Member m, Project p where m.userId = #{userId} and p.id = m.projectId and p.id = #{projectId}")
    Project findProjectByUidPid(Integer userId, Integer projectId);

    /**
     * 获取用户收到的通知
     *
     * @param userId 用户id
     * @return 用户收到的所有通知
     */
    @Select("select n.* from Notification n where n.receiverId = #{userId}")
    List<Notification> findRecvNotifications(Integer userId);

    /**
     * 获取用户收到的某条通知
     *
     * @param userId         用户id
     * @param notificationId 通知id
     * @return 若不存在则返回null
     */
    @Select("select n.* from Notification n where n.receiverId = #{userId} and n.id = #{notificationId}")
    Notification findRecvNotification(Integer userId, Integer notificationId);

    /**
     * 获取用户发送的通知
     *
     * @param userId 用户id
     * @return 用户发送的所有通知
     */
    @Select("select n.* from Notification n where n.senderId = #{userId}")
    List<Notification> findSendNotifications(Integer userId);

    /**
     * 获取用户发送的某条通知
     *
     * @param userId         用户id
     * @param notificationId 通知id
     * @return 若不存在则返回null
     */
    @Select("select n.* from Notification n where n.senderId = #{userId} and n.id = #{notificationId}")
    Notification findSendNotification(Integer userId, Integer notificationId);

    /**
     * 获取项目的超级管理员
     *
     * @param projectId 项目id
     * @return 项目的超级管理员
     */
    @Select("select u.* from User u, Member m where u.id = m.userId and m.projectId = #{projectId} and m.role = 'SuperAdmin'")
    User getSuperAdminByProjectId(Integer projectId);

    /**
     * 获取项目的管理员
     *
     * @param projectId 项目id
     * @return 项目的所有管理员
     */
    @Select("select u.* from User u, Member m where u.id = m.userId and m.projectId = #{projectId} and m.role = 'Admin'")
    List<User> getAdminsByProjectId(Integer projectId);

    /**
     * 更新通知
     *
     * @param notification 新通知
     */
    @Update("update Notification set `read` = #{read}, title = #{title}, body = #{body}, createAt = #{createAt}, senderId = #{senderId}, receiverId = #{receiverId} where id = #{id}")
    void updateNotification(Notification notification);

    /**
     * 保存通知
     *
     * @param notification 新通知
     */
    @Insert("insert into Notification(`read`, title, body, createAt, senderId, receiverId) values(#{read}, #{title}, #{body}, #{createAt}, #{senderId}, #{receiverId})")
    void insertNotification(Notification notification);
}

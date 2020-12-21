package com.ourprojmgr.demo.dao;

import com.ourprojmgr.demo.dbmodel.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
@Repository
public interface IProjectDao {

    //region Project Methods
    @Select("select * from Project where id = #{id}")
    Project getProjectById(int id);

    @Insert("insert into Project(id, name, description, createAt, updateAt) values (#{id}, #{name}, #{description}, #{createAt}, #{updateAt})")
    @Options(useGeneratedKeys = true)
    void insertProject(Project project);

    @Update("update Project set name = #{name}, description = #{description}, createAt = #{createAt}, updateAt = #{updateAt} where id = #{id}")
    void updateProject(Project project);

    @Delete("delete from Project where id = #{id}")
    void deleteProject(int id);
    //endregion

    //region Member Methods
    //@Select("select u.* from User u, Member m where u.id = m.userId and m.projectId = #{projectId}")
    //List<User> getAllMembers(int projectId);

    @Select("select * from Member where projectId = #{projectId}")
    List<Member> getMembersInProject(int projectId);

    @Select("select * from Member where userId = #{userId} and projectId = #{projectId}")
    Member getMember(int userId, int projectId);

    @Select("select count(*) from Member where userId = #{userId} and projectId = #{projectId}")
    int getMemberCount(int userId, int projectId);

    @Select("select count(*) from Member where userId = #{userId} and projectId = #{projectId} and role = #{role}")
    int getMemberRoleCount(int userId, int projectId, String role);

    @Insert("insert into Member(userId, projectId, role, joinAt) values(#{userId}, #{projectId}, #{role}, #{joinAt})")
    void insertMember(int userId, int projectId, String role, LocalDateTime joinAt);

    @Delete("delete from Member where userId = #{userId} and projectId = #{projectId}")
    void deleteMember(int userId, int projectId);
    //endregion

    //region Invitation Methods
    @Select("select * from Invitation where id = #{id}")
    Invitation getInvitationById(int id);

    @Select("select * from Invitation where projectId = #{projectId} and receiverId = #{receiverId} and status = #{status}")
    Invitation getInvitationByReceiver(int projectId, int receiverId, String status);

    @Insert("insert into Invitation(id, createAt, endAt, status, senderId, receiverId, projectId) values(#{id}, #{createAt}, #{endAt}, #{status}, #{senderId}, #{receiverId}, #{projectId})")
    @Options(useGeneratedKeys = true)
    void insertInvitation(Invitation invitation);

    @Select("select * from Invitation where projectId = #{projectId}")
    List<Invitation> getInvitationByProjectId(int projectId);

    @Update("update Invitation set createAt = #{createAt}, endAt = #{endAt}, status = #{status}, senderId = #{senderId}, receiverId = #{receiverId}, projectId = #{projectId} where id = #{id}")
    void updateInvitation(Invitation invitation);
    //endregion

    //region Task Methods
    @Select("select * from Task where id = #{id} and projectId = #{projectId}")
    Task getTaskById(int id, int projectId);

    @Select("select * from Task where projectId = #{projectId}")
    List<Task> getProjectTask(int projectId);

    @Insert("insert into Task(id, projectId, title, body, createAt, creatorId, complete, completeAt, completerId) values(#{id}, #{projectId}, #{title}, #{body}, #{createAt}, #{creatorId}, #{complete}, #{completeAt}, #{completerId})")
    @Options(useGeneratedKeys = true)
    void insertTask(Task task);

    @Update("update task set projectId = #{projectId}, title = #{title}, body = #{body}, createAt = #{createAt}, creatorId = #{creatorId}, complete = #{complete}, completeAt = #{completeAt}, completerId = #{completerId} where id = #{id}")
    void updateTask(Task task);

    @Delete("delete from task where id = #{id}")
    void deleteTask(int taskId);

    @Select("select u.* from User u, TaskExecutor ex where ex.taskId = #{taskId} and ex.executorId = u.id")
    List<User> getExecutors(int taskId);

    @Insert("insert into TaskExecutor(taskId, executorId) values(#{taskId}, #{executorId})")
    void insertExecutor(int taskId, int executorId);

    @Delete("delete from TaskExecutor where taskId = #{taskId} and executorId = #{executorId}")
    void deleteExecutor(int taskId, int executorId);
    //endregion

    //region Comment Methods
    @Select("select * from Comment where taskId = #{taskId}")
    List<Comment> getTaskComment(int taskId);

    @Select("select * from Comment where id = #{id} and taskId = #{taskId}")
    Comment getCommentById(int id, int taskId);

    @Select("select count(*) from Comment where taskId = #{taskId}")
    int getCommentCount(int taskId);

    @Update("update Comment set taskId = #{taskId}, body = #{body}, createAt = #{createAt}, userId = #{userId} where id = #{id}")
    void updateComment(Comment comment);

    @Insert("insert Comment(id, taskId, body, createAt, userId) values(#{id}, #{taskId}, #{body}, #{createAt}, #{userId})")
    @Options(useGeneratedKeys = true)
    void insertComment(Comment comment);

    @Delete("delete from Comment where id = #{id}")
    void deleteComment(int id);
    //endregion
}

package com.ourprojmgr.demo.dao;

import com.ourprojmgr.demo.dbmodel.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
@Repository
public interface IProjectDao {

	@Select("select * from Project where id = #{id}")
	public Project getProjectById(int id);

	@Insert("insert into Project(id, name, description, createAt, updateAt) values (#{id}, #{name}, #{description}, #{createAt}, #{updateAt}")
	public Project insertProject(Project project);

	@Update("update Project set name = #{name}, description = #{description}, createAt = #{createAt}. updateAt = #{updateAt} where id = #{id}")
	public void updateProject(Project project);

	@Delete("delete from Project where id = #{id}")
	public void deleteProject(int id);

	@Select("select u.* from User u, Member m where u.id = m.userId and m.projectId = #{projectId} and m.role = 'Member'")
	public List<User> getAllMembers(int projectId);

	@Select("select count(*) from Member m where m.userId = #{userId} and m.projectId = #{projectId}")
	public int getMemberCount(int userId, int projectId);

	@Select("select count(*) from Member m where m.userId = #{userId} and m.projectId = #{projectId} and role = #{role}")
	public int getMemberCount(int userId, int projectId, String role);

	@Select("select * from Invitation where id = #{id}")
	public Invitation getInvitationById(int id);

	@Insert("insert into Invitation(id, createAt, endAt, status, senderId, receiverId, projectId) values(#{id}, #{createAt}, #{endAt}, #{status}, #{senderId}, #{receiverId}, #{projectId}")
	public void insertInvitation(Invitation invitation);
	
	@Select("select * from Invitation where projectId = #{projectId}")
	public List<Invitation> getInvitationByProjectId(int projectId);

	@Update("update Invitation set createAt = #{createAt}, endAt = #{endAt}, status = #{status}, senderId = #{senderId}, receiverId = #{receiverId}, projectId = #{projectId} where id = #{id}")
	public void updateInvitation(Invitation invitation);

	@Insert("insert into Member(userId, projectId, role, joinAt) values(#{userId}, #{projectId}, #{role}, #{joinAt})")
	public void insertMember(int userId, int projectId, String role, LocalDateTime joinAt);

	@Delete("delete from Member where userId = #{userId} and projectId = #{projectId}")
	public void deleteMember(int userId, int projectId);

	@Select("select * from Comment where taskId = #{taskId}")
	public List<Comment> getTaskComment(int taskId);

	@Select("select * from Task where id = #{id}")
	public Task getTaskById(int id);

	@Select("select * from Comment where id = #{id} and taskId = #{taskId}")
	public Comment getComment(int id, int taskId);

	@Update("update Comment set taskId = #{taskId}, body = #{body}, createAt = #{createAt}, userId = #{userId} where id = #{id}")
	public void updateComment(Comment comment);

	@Insert("insert Comment(id, taskId, body, createAt, userId) values(#{id}, #{taskId}, #{body}, #{createId}, #{userId}")
	public void insertComment(Comment comment);

	@Delete("delete from Comment where id = #{id}")
	public void deleteComment(int id);
}

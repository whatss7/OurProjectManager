package com.ourprojmgr.demo.service.impl;

import com.ourprojmgr.demo.dbmodel.Project;
import com.ourprojmgr.demo.dbmodel.User;
import com.ourprojmgr.demo.jsonmodel.UserJson;
import com.ourprojmgr.demo.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * IUserService 的实现类
 *
 * @author 董晓艺
 */
@Service
public class UserServiceImpl implements IUserService {
    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public User getUserByName(String username) {
        return null;
    }

    @Override
    public UserJson userToJson(User user) {
        return null;
    }

    @Override
    public void updatePassword(User user, String oldPassword, String newPassword) {

    }

    @Override
    public void updateUsernameAndNickname(User user, String newUsername, String newNickname) {

    }

    @Override
    public int getProjectCount(User user) {
        return 0;
    }

    @Override
    public List<Project> getUserProjects(User user) {
        return null;
    }
}

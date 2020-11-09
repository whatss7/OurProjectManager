package com.ourprojmgr.demo.service;

import com.ourprojmgr.demo.dbmodel.User;

public interface IUserService {
    User getUserById(int id);
    User getUserByName(String username);
}

package com.empathy.restapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.empathy.restapi.model.User;
import com.empathy.restapi.service.UserService;
import com.empathy.restapi.util.UserBD;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserBD userBD;

    @Override
    public List<User> getUsers() {
        return userBD.getUsers();
    }

    @Override
    public User getUserById(long id) {
        return userBD.getUserById(id);
    }

    @Override
    public User save(User user) {
        userBD.addUser(user);

        return user;
    }

    @Override
    public void delete(User user) {
        userBD.deleteUserById(user);
    }

}

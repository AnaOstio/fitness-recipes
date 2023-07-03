package com.empathy.restapi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empathy.restapi.model.User;
import com.empathy.restapi.service.UserService;
import com.empathy.restapi.util.UserBD;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserBD userBD;

    @Override
    public List<User> getUsers() {
        return userBD.getUsers();
    }

    @Override
    public Optional<User> getUserById(String id) {
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

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userBD.getUserByEmail(email);
    }
}

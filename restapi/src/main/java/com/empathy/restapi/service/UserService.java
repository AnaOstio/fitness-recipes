package com.empathy.restapi.service;

import java.util.List;

import com.empathy.restapi.model.User;

public interface UserService {

    List<User> getUsers();

    User getUserById(long id);

    User save(User user);

    void delete(User user);
}

package com.empathy.restapi.service;

import java.util.List;
import java.util.Optional;

import com.empathy.restapi.model.User;

public interface UserService {

    List<User> getUsers();

    Optional<User> getUserById(String id);

    User save(User user);

    void delete(User user);
}

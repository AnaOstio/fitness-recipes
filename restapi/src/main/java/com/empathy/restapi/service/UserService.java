package com.empathy.restapi.service;

import java.util.List;
import java.util.Optional;

import com.empathy.restapi.model.Recipe;
import com.empathy.restapi.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    List<User> getUsers();
    Optional<User> getUserById(String id);
    User save(User user);
    void delete(User user);

    Optional<User> getUserByEmail(String email);

    Optional<User> getUserByUsername(String username);

    UserDetails loadUserByUsername(String userName);

    String loginUser(String email, String password);

    User findByUsername(String username);
}

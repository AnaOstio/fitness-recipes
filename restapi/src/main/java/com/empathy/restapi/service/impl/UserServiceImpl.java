package com.empathy.restapi.service.impl;

import java.util.List;
import java.util.Optional;

import com.empathy.restapi.security.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.empathy.restapi.model.User;
import com.empathy.restapi.service.UserService;
import com.empathy.restapi.util.UserBD;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserBD userBD;

    @Autowired
    private TokenUtil tokenUtil;

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

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userBD.getUserByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userBD.getUserByEmail(username).get();
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities("COMMON_USER")
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

    @Override
    public String loginUser(String email, String password) {
        // Find the user by username
        User user = userBD.getUserByEmail(email).get();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Check if the user exists and the provided password matches the stored encoded password
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {

            UserDetails userDetails = loadUserByUsername(email);
            // Generate and return a JWT token
            String token = tokenUtil.generateToken(userDetails);

            return token;
        }

        // Return null if authentication fails
        return null;
    }

}

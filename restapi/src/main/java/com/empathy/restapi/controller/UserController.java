package com.empathy.restapi.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empathy.restapi.model.User;
import com.empathy.restapi.service.UserService;
import com.empathy.restapi.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userService = userServiceImpl;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<List<User>>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<String> addUser(@RequestBody User newUser) {
        User user = userService.save(newUser);

        if (user != null)
            return new ResponseEntity<>("User created successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("User could not be created", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> addUser(@PathVariable long id) {
        User user = userService.getUserById(id);

        if (user != null) {
            userService.delete(user);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } else
            return new ResponseEntity<>("User does not exist", HttpStatus.BAD_REQUEST);
    }
}

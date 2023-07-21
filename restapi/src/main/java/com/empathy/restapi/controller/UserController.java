package com.empathy.restapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.empathy.restapi.security.util.AuthCredentials;
import com.empathy.restapi.security.util.AuthResponse;
import com.empathy.restapi.security.util.TokenUtil;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.empathy.restapi.model.User;
import com.empathy.restapi.service.UserService;
import com.empathy.restapi.service.impl.UserServiceImpl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/")
public class UserController {

    private final UserService userService;
    private final TokenUtil tokenUtil;


    public UserController(UserService userService, TokenUtil tokenUtil) {
        this.userService = userService;
        this.tokenUtil = tokenUtil;
    }

    @Autowired
    public UserController(UserServiceImpl userServiceImpl, TokenUtil tokenUtil) {
        this.userService = userServiceImpl;
        this.tokenUtil = tokenUtil;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<List<User>>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        return new ResponseEntity<User>(userService.getUserById(id).get(), HttpStatus.OK);
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
    public ResponseEntity<String> addUser(@PathVariable String id) {
        User user = userService.getUserById(id).get();

        if (user != null) {
            userService.delete(user);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } else
            return new ResponseEntity<>("User does not exist", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/authenticate/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody AuthCredentials user, HttpServletResponse response2) {

        try {
            // Call the loginUser method of the userService to authenticate the user and generate JWT token
            String token = userService.loginUser(user.getEmail(), user.getPassword());
            String id = userService.getUserByEmail(user.getEmail()).get().getId();
            if(token == null) {
                Map<String, Object> response = new HashMap<>();
                response.put("message", "Invalid username or password");
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            }

            AuthResponse authDTO = new AuthResponse(token, user.getEmail(),id);
            Map<String, Object> response = new HashMap<>();
            response.put("token", authDTO.getToken());
            response.put("email", authDTO.getUsername());
            response.put("id", authDTO.getId());
            response.put("status", HttpStatus.OK.value());

            Cookie jwtCookie = new Cookie("jwt", token);
            jwtCookie.setPath("/");
            jwtCookie.setHttpOnly(true);
            jwtCookie.setSecure(false);
            response2.addCookie(jwtCookie);

            response2.addHeader("Authorization", "Bearer " + token);

            ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);

            return responseEntity;
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }
}

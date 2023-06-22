package com.empathy.restapi.util;

import java.util.List;

import com.empathy.restapi.model.User;

public class UserBD {

    private List<User> userList;

    public void setuserList(List<User> userList) {
        this.userList = userList;
    }

    public List<User> getUsers() {
        return userList;
    }

    public void addUser(User user) {
        this.userList.add(user);
    }

    public User getUserById(long id) {
        return this.userList.stream().filter((user) -> user.getId() == id).findFirst().get();
    }

    public void deleteUserById(User user) {
        this.userList.remove(user);
    }
}

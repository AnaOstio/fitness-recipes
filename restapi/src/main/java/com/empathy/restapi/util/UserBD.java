package com.empathy.restapi.util;

import java.util.List;
import java.util.Optional;

import com.empathy.restapi.model.User;

public class UserBD {

    private List<User> userList;

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<User> getUsers() {
        return userList;
    }

    public void addUser(User user) {
        this.userList.add(user);
    }

    public Optional<User> getUserById(String id) {
        return this.userList.stream().filter((user) -> user.getId().equals(id)).findFirst();
    }

    public void deleteUserById(User user) {
        this.userList.remove(user);
    }

    public void addRecipeUser(String recipeId, String userId){
        this.userList.stream().filter((user) -> user.getId().equals(userId)).findFirst().get().addRecipe(recipeId);
    }
}

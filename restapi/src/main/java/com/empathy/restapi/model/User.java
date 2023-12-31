package com.empathy.restapi.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String id;
    private String username;
    private String email;
    private String password;
    private List<String> userRecipesId;

    public User(String id, String username, String email, String password, List<String> userRecipesId) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userRecipesId = userRecipesId;
    }

    public User(String id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userRecipesId = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getUserRecipesId() {
        return userRecipesId;
    }

    public void setUserRecipesId(List<String> userRecipesId) {
        this.userRecipesId = userRecipesId;
    }

    public void addRecipe(String recipeId){
        this.userRecipesId.add(recipeId);
    }

    public void deleteRecipe(String recipeId) {
        this.userRecipesId.remove(recipeId);
    }
}

package com.empathy.restapi.security.util;

public class AuthResponse {

    private String token;
    private String id;
    private String username;

    public AuthResponse(String token, String email, String id) {
        this.token = token;
        this.id = id;
        this.username = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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
}

package com.example.tema3.model;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
    private Long id;
    private String name;
    private String username;
    private String email;

    public User(){
    }

    public User(Long id, String name, String username, String email) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
    }

    public static User fromJson(String json) throws JSONException {
        JSONObject userJson = new JSONObject(json);
        Long id = userJson.getLong("id");
        String name = userJson.getString("name");
        String userName = userJson.getString("username");
        String email = userJson.getString("email");
        return new User(id, name, userName, email);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}

package com.example.tema3.model;

import org.json.JSONException;
import org.json.JSONObject;

public class ToDo {
    private Long userId;
    private Long id;
    private String title;
    private Boolean completed;

    public ToDo() {
    }

    public ToDo(Long userId, Long id, String title, Boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public static ToDo fromJson(String json) throws JSONException {
        JSONObject userJson = new JSONObject(json);
        Long userId = userJson.getLong("userId");
        Long id = userJson.getLong("id");
        String title = userJson.getString("title");
        Boolean completed = userJson.getBoolean("completed");
        return new ToDo(userId, id, title, completed);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}

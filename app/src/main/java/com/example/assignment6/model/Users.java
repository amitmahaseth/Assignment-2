package com.example.assignment6.model;

import com.google.gson.annotations.SerializedName;

public class Users {
    @SerializedName("userId")
    private String userId;
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String uName;
    @SerializedName("username")
    private String userName;
    @SerializedName("title")
    private String title;

    public Users(String userId, String id, String uName, String userName, String title) {
        this.userId = userId;
        this.id = id;
        this.uName = uName;
        this.userName = userName;
        this.title = title;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

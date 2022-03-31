package com.tracing.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

    User() {}

    public User(Long userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    private @Id @GeneratedValue Long id;
    private Long userId;
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUser() {
        return username;
    }

    public void setUser(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", userId=" + userId + ", username=" + username + "]";
    }
}
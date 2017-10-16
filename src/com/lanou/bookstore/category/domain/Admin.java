package com.lanou.bookstore.category.domain;

import java.io.Serializable;

/**
 * Created by dllo on 17/9/22.
 */
public class Admin implements Serializable {

    private String aid;

    private String username;

    private String password;

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

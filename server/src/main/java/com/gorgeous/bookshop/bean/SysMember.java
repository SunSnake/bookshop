package com.gorgeous.bookshop.bean;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author Gorgeous
 * @date 2022/10/08 15:33
 */
@Component
public class SysMember implements Serializable {

    private static final long serialVersionUID = -7628265553375667168L;

    private String uid;

    private String username;

    private String password;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

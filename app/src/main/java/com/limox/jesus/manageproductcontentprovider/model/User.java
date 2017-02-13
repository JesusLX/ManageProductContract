package com.limox.jesus.manageproductcontentprovider.model;

/**
 * Created by jesus on 20/10/16.
 */

public class User {
    private String user;
    private String password;
    private String email;

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String user, String password, String email) {
        this.user = user;
        this.password = password;
        this.email = email;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return  "user='" + user +", password='" + password ;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

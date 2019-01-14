package com.marlon.module.common.javabean.request;

/**
 * @author Marlon
 * @desc
 * @date 2018/12/26
 */
public class LoginRequset {
    private String username;
    private String password;

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

    @Override
    public String toString() {
        return "LoginRequset{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

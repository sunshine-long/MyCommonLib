package com.marlon.module.common.javabean.request;

/**
 * @author Marlon
 * @desc
 * @date 2019/1/9
 */
public class TokenRequest {
    private String token;

    public TokenRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

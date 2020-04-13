package com.codesityou.javalintokens.entities;

public class AuthResponse {

    private String userId;
    private String token;
    private boolean success;

    public AuthResponse(){

    }

    public AuthResponse(String userId, String token, boolean success) {
        this.userId = userId;
        this.token = token;
        this.success = success;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    
    public static AuthResponse bad() {
        return new AuthResponse(null, null, false);
    }
}
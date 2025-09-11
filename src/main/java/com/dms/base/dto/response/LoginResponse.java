package com.dms.base.dto.response;

public class LoginResponse {

    private String accessToken;
    private String refreshToken;
    private long expiresIn;
    private UserLoginResponse user;

    public LoginResponse(String accessToken, UserLoginResponse user) {
        this.accessToken = accessToken;
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public UserLoginResponse getUser() {
        return user;
    }

    public void setUser(UserLoginResponse user) {
        this.user = user;
    }
}

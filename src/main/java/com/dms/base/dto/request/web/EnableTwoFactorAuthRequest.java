package com.dms.base.dto.request.web;

public class EnableTwoFactorAuthRequest {
    private String secretKey; 

    public EnableTwoFactorAuthRequest() {}

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}

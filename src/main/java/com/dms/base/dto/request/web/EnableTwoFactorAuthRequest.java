package com.dms.base.dto.request.web;

public class EnableTwoFactorAuthRequest {
    private String verificationCode; 

    public EnableTwoFactorAuthRequest() {}

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}

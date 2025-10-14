package com.dms.base.dto.request.web;

public class VerivyTwoFactorAuthRequest {
    private Long userId;
    private String verificationCode;

    public VerivyTwoFactorAuthRequest() {
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}

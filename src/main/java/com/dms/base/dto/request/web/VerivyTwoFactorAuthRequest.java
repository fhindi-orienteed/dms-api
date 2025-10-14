package com.dms.base.dto.request.web;

public class VerivyTwoFactorAuthRequest {
    private Long userId;
    private String code;

    public VerivyTwoFactorAuthRequest() {
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

package com.dms.base.dto.response.mobile;

import com.dms.base.dto.response.common.LoginResponse;

public class MobileLoginResponse extends LoginResponse {
    private MobileUserResponse user;

    public MobileLoginResponse(String accessToken, MobileUserResponse user) {
        this.accessToken = accessToken;
        this.user = user;
    }

    public MobileUserResponse getUser() {
        return user;
    }

    public void setUser(MobileUserResponse user) {
        this.user = user;
    }

}

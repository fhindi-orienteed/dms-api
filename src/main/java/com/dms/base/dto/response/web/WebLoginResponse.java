package com.dms.base.dto.response.web;

import com.dms.base.dto.response.common.LoginResponse;

public class WebLoginResponse extends LoginResponse {
    private WebUserResponse user;

    public WebLoginResponse(String accessToken, WebUserResponse user) {
        this.accessToken = accessToken;
        this.user = user;
    }

    public WebUserResponse getUser() {
        return user;
    }

    public void setUser(WebUserResponse user) {
        this.user = user;
    }
}

package com.dms.base.dto.response.web;

import com.dms.base.dto.response.common.LoginResponse;

public class WebLoginResponse extends LoginResponse {
    private WebUserResponse user;
    private boolean enabled;
    private String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}

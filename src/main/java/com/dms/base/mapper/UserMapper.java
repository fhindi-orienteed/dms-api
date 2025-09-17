package com.dms.base.mapper;

import org.springframework.stereotype.Component;

import com.dms.base.dto.response.UserLoginResponse;
import com.dms.base.dto.response.mobile.MobileUserResponse;
import com.dms.base.dto.response.web.WebUserResponse;
import com.dms.base.model.User;

@Component
public class UserMapper {

    public UserLoginResponse mapUserLoginResponse(User user) {
        UserLoginResponse res = new UserLoginResponse();
        res.setId(user.getId());
        return res;
    }

    public MobileUserResponse mapToMobileResponse(User user) {
        MobileUserResponse res = new MobileUserResponse();
        res.setId(user.getId());
        res.setCreatedDate(user.getCreatedDate());
        res.setUserName(user.getUserName());
        res.setStatus(user.getStatus());
        res.setRole(user.getRole());
        res.setPasswordRetries(user.getPasswordRetries());
        res.setPasswordExpired(user.isPasswordExpired());
        res.setTimeout(user.getTimeout());
        res.setLastSession(user.getLastSession());
        return res;
    }

    public WebUserResponse mapToWebResponse(User user) {
        WebUserResponse res = new WebUserResponse();
        res.setId(user.getId());
        res.setCreatedDate(user.getCreatedDate());
        res.setUserName(user.getUserName());
        return res;
    }
}

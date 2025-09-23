package com.dms.base.mapper;

import org.springframework.stereotype.Component;

import com.dms.base.dto.response.mobile.MobileUserResponse;
import com.dms.base.dto.response.web.WebUserResponse;
import com.dms.base.model.User;

@Component
public class UserMapper {

    public MobileUserResponse mapToMobileResponse(User user) {
        MobileUserResponse res = new MobileUserResponse();
        res.setId(user.getId());
        res.setCreatedDate(user.getCreatedDate());
        res.setLastSession(user.getLastSession());
        res.setUserName(user.getUserName());
        res.setRole(user.getRole());
        res.setStatus(user.getStatus());
        res.setLastSession(user.getLastSession());
        return res;
    }

    public WebUserResponse mapToWebResponse(User user) {
        WebUserResponse res = new WebUserResponse();
        res.setId(user.getId());
        res.setCreatedDate(user.getCreatedDate());
        res.setLastSession(user.getLastSession());
        res.setUserName(user.getUserName());
        res.setRole(user.getRole());
        res.setStatus(user.getStatus());
        res.setLastSession(user.getLastSession());
        return res;
    }
}

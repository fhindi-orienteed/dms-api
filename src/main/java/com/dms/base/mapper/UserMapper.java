package com.dms.base.mapper;

import org.springframework.stereotype.Component;

import com.dms.base.dto.request.web.CreateNewUserRequest;
import com.dms.base.dto.response.UserLoginResponse;
import com.dms.base.dto.response.mobile.MobileUserResponse;
import com.dms.base.dto.response.web.WebUserResponse;
import com.dms.base.model.User;
import java.util.Date;

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
        return res;
    }

    public WebUserResponse mapToWebResponse(User user) {
        WebUserResponse response = new WebUserResponse();
        response.setId(user.getId());
        response.setRole(user.getRole());
        response.setUserName(user.getUserName());
        response.setCreatedDate(user.getCreatedDate());
        response.setStatus(user.getStatus());
        response.setCreatedDate(new Date()); // set current date
        response.setStatus(1); // assuming 1 means active
        response.setPasswordRetries(0);
        response.setPasswordExpired(0);
        response.setPasswordCreation(0);
        response.setTimeout(1800); // example timeout in seconds
        return response;
    }
  
}

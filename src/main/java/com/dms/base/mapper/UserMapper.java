package com.dms.base.mapper;

import org.springframework.stereotype.Component;

import com.dms.base.dto.request.web.CreateNewUserRequest;
import com.dms.base.dto.response.UserLoginResponse;
import com.dms.base.dto.response.mobile.MobileUserResponse;
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

    public User mapToCreateWebUserRequest(CreateNewUserRequest newUserRequest) {
        User usr = new User();
        usr.setRole(newUserRequest.getRole());
        usr.setUserName(newUserRequest.getUserName());
        usr.setPassword(newUserRequest.getPassword());
        usr.setCreatedDate(new Date()); // set current date
        usr.setStatus(1); // assuming 1 means active
        usr.setPasswordRetries(0);
        usr.setPasswordExpired(0);
        usr.setPasswordCreation(0);
        usr.setTimeout(1800); // example timeout in seconds
        return usr;
    }

}

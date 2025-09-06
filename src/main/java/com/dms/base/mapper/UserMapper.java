package com.dms.base.mapper;

import org.springframework.stereotype.Component;

import com.dms.base.dto.response.UserLoginResponse;
import com.dms.base.model.User;

@Component
public class UserMapper {

    public UserLoginResponse mapUserLoginResponse(User user) {
        UserLoginResponse res = new UserLoginResponse();
        res.setId(user.getId());
        return res;
    }

}

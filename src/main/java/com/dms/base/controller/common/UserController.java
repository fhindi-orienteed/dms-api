package com.dms.base.controller.common;

import org.springframework.beans.factory.annotation.Autowired;

import com.dms.base.mapper.UserMapper;
import com.dms.base.service.UserService;

public abstract class UserController {
    @Autowired
    protected UserService userService;

    @Autowired
    protected UserMapper userMapper;
}

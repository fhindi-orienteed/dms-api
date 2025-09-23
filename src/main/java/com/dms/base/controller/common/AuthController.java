package com.dms.base.controller.common;

import com.dms.base.mapper.UserVerifyMapper;
import com.dms.base.service.AddressService;
import com.dms.base.service.AuthService;
import com.dms.base.service.UserService;
import com.dms.base.service.UserVerifyService;

import org.springframework.beans.factory.annotation.Autowired;

public class AuthController {
    @Autowired
    protected AuthService authService;
    
    @Autowired
    protected UserService userService;
    
    @Autowired
    protected UserVerifyService userVerifyService;

    @Autowired
    protected UserVerifyMapper userVerifyMapper;

    @Autowired
    protected AddressService addressService;
}

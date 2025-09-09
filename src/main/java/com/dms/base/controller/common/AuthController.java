package com.dms.base.controller.common;

import com.dms.base.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthController {
    @Autowired
    protected AuthService authService;
}

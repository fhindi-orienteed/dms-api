package com.dms.base.service;

import com.dms.base.dto.LoginRequest;
import com.dms.base.dto.LoginResponse;


public interface AuthService {
 LoginResponse login(LoginRequest request);
}

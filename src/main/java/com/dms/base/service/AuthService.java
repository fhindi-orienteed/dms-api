package com.dms.base.service;

import com.dms.base.dto.request.LoginRequest;
import com.dms.base.dto.response.LoginResponse;


public interface AuthService {
 LoginResponse login(LoginRequest request);
}

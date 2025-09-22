package com.dms.base.mapper;

import org.springframework.stereotype.Component;

import com.dms.base.dto.response.mobile.MobileVerifyEmailResponse;
import com.dms.base.model.UserVerify;

@Component
public class UserVerifyMapper {
    
    public MobileVerifyEmailResponse mapTowebResponse(UserVerify record) {
        MobileVerifyEmailResponse response = new MobileVerifyEmailResponse();
        response.setEmail(record.getEmail());
        response.setExpiresAt(record.getExpiresAt());
        response.setMobile(record.getMobile());
        response.setCode(record.getCode());
        response.setStatus(record.getStatus());
        return response;
    }
}

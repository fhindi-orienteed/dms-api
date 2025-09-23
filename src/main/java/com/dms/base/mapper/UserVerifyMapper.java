package com.dms.base.mapper;

import org.springframework.stereotype.Component;

import com.dms.base.dto.response.mobile.MobileVerifyEmailResponse;
import com.dms.base.model.UserVerify;

@Component
public class UserVerifyMapper {
    
    public MobileVerifyEmailResponse mapToMobileResponse(UserVerify record) {
        MobileVerifyEmailResponse response = new MobileVerifyEmailResponse();
        response.setChannel(record.getChannel());
        response.setEmail(record.getTarget());
        response.setExpiresAt(record.getExpiresAt());
        response.setCode(record.getCode());
        response.setCreatedDate(record.getCreatedDate());
        response.setStatus(record.getStatus());
        return response;
    }
}

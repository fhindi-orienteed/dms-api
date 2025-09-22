package com.dms.base.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.base.model.UserVerify;
import com.dms.base.repository.UserVerifyRepository;

@Service
public class UserVerifyService {
    @Autowired
    private UserVerifyRepository userVerifyRepository;

    public UserVerify createNewVerficationRecord(long userId, String email, String mobile, LocalDateTime createdDate, String Code, LocalDateTime expiresAt, String status) {
        UserVerify newRecord = new UserVerify();
        newRecord.setUserId(userId);
        newRecord.setEmail(email);
        newRecord.setMobile(mobile);
        newRecord.setCreatedDate(createdDate);
        newRecord.setCode(Code);
        newRecord.setExpiresAt(expiresAt);
        newRecord.setStatus(status);
        userVerifyRepository.save(newRecord);
        return newRecord;
    }
}

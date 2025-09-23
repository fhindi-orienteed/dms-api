package com.dms.base.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dms.base.model.Address;
import com.dms.base.model.UserVerify;
import com.dms.base.repository.UserVerifyRepository;
import com.dms.base.util.Constant;
import com.dms.base.util.Constant.Channel;
import com.dms.base.util.Constant.VerificationCodeStatus;

@Service
public class UserVerifyService {
    @Autowired
    private UserVerifyRepository userVerifyRepository;

    public UserVerify createNewVerficationRecord(long userId, String target, String channel, LocalDateTime createdDate,
            String Code, LocalDateTime expiresAt, String status) {
        UserVerify newRecord = new UserVerify();
        newRecord.setUserId(userId);
        newRecord.setChannel(channel);
        newRecord.setTarget(target);
        newRecord.setCreatedDate(createdDate);
        newRecord.setCode(Code);
        newRecord.setExpiresAt(expiresAt);
        newRecord.setStatus(status);
        userVerifyRepository.save(newRecord);
        return newRecord;
    }

    public UserVerify verifyEmail(Address address) {
        String code = generateVerificationCode(Constant.NUMBER_OF_EMAIL_VERIFICATION_CHARCTERS,
                Constant.BOOlEAN_OF_EMAIL_VERIFICATION);
        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(10);
        String channel = Channel.EMAIL.name();
        String status = VerificationCodeStatus.PENDING.name();
        UserVerify newRecord = createNewVerficationRecord(address.getUserId(),address.getEmail() ,channel, LocalDateTime.now(), code,
                expiresAt, status);
        return newRecord;
    }

    public UserVerify verifyMobile(Address address) {
        String code = generateVerificationCode(Constant.NUMBER_OF_MOBILE_VERIFICATION_DIGITS,
                Constant.BOOlEAN_OF_MOBILE_VERIFICATION);
        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(10);
        String channel = Channel.MOBILE.name();
        String status = VerificationCodeStatus.PENDING.name();
        UserVerify newRecord = createNewVerficationRecord(address.getUserId(), address.getMobile() ,channel, LocalDateTime.now(), code,
                expiresAt, status);
        return newRecord;
    }

    public String generateVerificationCode(int length, boolean onlyNumber) {
        String characters;
        if (onlyNumber) {
            characters = "0123456789";
        } else {
            characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        }

        SecureRandom random = new SecureRandom();
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            code.append(characters.charAt(index));
        }

        return code.toString();
    }

}

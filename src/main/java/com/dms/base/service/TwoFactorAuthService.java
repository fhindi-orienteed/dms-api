package com.dms.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.base.dto.response.web.WebTwoFactorAuthResponse;
import com.dms.base.exception.BadRequestException;
import com.dms.base.exception.ObjectNotFoundException;
import com.dms.base.model.TwoFactorAuth;
import com.dms.base.model.User;
import com.dms.base.repository.TwoFactorAuthRepository;
import com.dms.base.repository.UserRepository;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import com.dms.base.util.Constant.TwoFactorAuthType;

@Service
public class TwoFactorAuthService {
    @Autowired
    private TwoFactorAuthRepository twoFactorAuthRepository;
    @Autowired
    private UserRepository userRepository;

    private final GoogleAuthenticator gAuth = new GoogleAuthenticator();
    private final String ISSUER = "Delivery System";

    public WebTwoFactorAuthResponse setupTwoFactorAuth(User user) {
        TwoFactorAuth exist2FA = twoFactorAuthRepository.findByUserId(user.getId());

        if (exist2FA != null) {
            return createExisting2FAResponse(user, exist2FA);
        } else {
            return createNew2FAResponse(user);
        }
    }

    public boolean enableTwoFactorAuth(User user, String providedSecretKey) {
        TwoFactorAuth exist2FA = twoFactorAuthRepository.findByUserId(user.getId());

        if (exist2FA == null) {
            throw new ObjectNotFoundException("2FA not set up for this user. Please setup 2FA first.");
        }

        if (exist2FA.isEnabled()) {
            throw new BadRequestException("2FA is already enabled for this user.");
        }

        if (exist2FA.getVerificationCode().equals(providedSecretKey)) {
            exist2FA.setEnabled(true);
            twoFactorAuthRepository.save(exist2FA);
            return true;
        } else {
            throw new BadRequestException("Invalid secret key. Please check and try again.");
        }
    }

    public boolean disableTwoFactorAuth(User user) {
        TwoFactorAuth exist2FA = twoFactorAuthRepository.findByUserId(user.getId());
        if (exist2FA == null) {
            throw new ObjectNotFoundException("2FA is not set up for this user.");
        }

        if (!exist2FA.isEnabled()) {
            throw new BadRequestException("2FA is already disabled for this user.");
        }

        exist2FA.setEnabled(false);
        twoFactorAuthRepository.save(exist2FA);
        return true;
    }

    public boolean verifyTwoFactorAuth(long userId, String secretKey) {
        User existUser = userRepository.findById(userId).orElse(null);

        if (existUser == null) {
            throw new ObjectNotFoundException("User not found with ID: " + userId);
        }

        TwoFactorAuth twoFactorAuth = twoFactorAuthRepository.findByUserId(userId);
        if (twoFactorAuth == null) {
            throw new BadRequestException("2FA is not set up for this user");
        }

        if (!twoFactorAuth.isEnabled()) {
            throw new BadRequestException("2FA is not enabled for this user");
        }

        return twoFactorAuth.getVerificationCode().equals(secretKey);
    }

    private WebTwoFactorAuthResponse createExisting2FAResponse(User user, TwoFactorAuth exist2FA) {
        String qrCodeUrl = generateQRCodeUrl(user.getUserName(), exist2FA.getVerificationCode());

        WebTwoFactorAuthResponse response = new WebTwoFactorAuthResponse();
        response.setQrCodeUrl(qrCodeUrl);
        response.setNewlyGenerated(false);
        return response;
    }

    private WebTwoFactorAuthResponse createNew2FAResponse(User user) {
        GoogleAuthenticatorKey key = gAuth.createCredentials();
        String secretKey = key.getKey();
        String qrCodeUrl = generateQRCodeUrl(user.getUserName(), key);

        createNewTwoFactorRecord(user.getId(), secretKey);

        WebTwoFactorAuthResponse response = new WebTwoFactorAuthResponse();
        response.setQrCodeUrl(qrCodeUrl);
        response.setNewlyGenerated(true);
        return response;
    }

    private void createNewTwoFactorRecord(long userId, String secretKey) {
        TwoFactorAuth newTwoFactorAuth = new TwoFactorAuth();
        newTwoFactorAuth.setUserId(userId);
        newTwoFactorAuth.setVerificationCode(secretKey);
        newTwoFactorAuth.setType(TwoFactorAuthType.APP.name());
        twoFactorAuthRepository.save(newTwoFactorAuth);
    }

    private String generateQRCodeUrl(String userName, GoogleAuthenticatorKey key) {
        return GoogleAuthenticatorQRGenerator.getOtpAuthURL(ISSUER, userName, key);
    }

    private String generateQRCodeUrl(String userName, String secretKey) {
        GoogleAuthenticatorKey key = new GoogleAuthenticatorKey.Builder(secretKey).build();
        return GoogleAuthenticatorQRGenerator.getOtpAuthURL(ISSUER, userName, key);
    }
}

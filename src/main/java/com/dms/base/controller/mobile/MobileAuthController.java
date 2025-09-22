package com.dms.base.controller.mobile;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;
import com.dms.base.controller.common.AuthController;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.dms.base.exception.InvalidCredentialsException;
import com.dms.base.mapper.UserVerifyMapper;
import com.dms.base.model.User;
import com.dms.base.model.UserVerify;
import com.dms.base.service.UserService;
import com.dms.base.service.UserVerifyService;
import com.dms.base.util.Constant.VerificationCodeStatus;
import com.dms.base.exception.AccountLockedException;
import com.dms.base.dto.request.LoginRequest;
import com.dms.base.dto.response.common.LoginResponse;
import com.dms.base.dto.response.mobile.MobileVerifyEmailResponse;

@RestController
@RequestMapping("/v1/mobile/auth")
@Tag(name = "Authentication API", description = "These services provide APIs related to authentication and session management for DMS Mobile Application.")
public class MobileAuthController extends AuthController {
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserVerifyService userVerifyService;

    @Autowired
    private UserVerifyMapper userVerifyMapper;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            LoginResponse response = authService.mobileLogin(request.getUserName(), request.getPassword());
            return ResponseEntity.ok(response);
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "INVALID_CREDENTIALS", "message", "User name or password is incorrect"));
        } catch (AccountLockedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(Map.of("error", "ACCOUNT_LOCKED", "message",
                            "Your account has been locked. Please contact support."));
        }
    }

    @PostMapping("/email-verify")
    public ResponseEntity<MobileVerifyEmailResponse> verifyEmail() {
        User currentUser = userService.getCurrentUser();
        String code = authService.generateVerificationCode();
        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(10);
        String status = VerificationCodeStatus.PENDING.name();
        UserVerify newRecord = userVerifyService.createNewVerficationRecord(currentUser.getId(),currentUser.getEmail(),currentUser.getMobile(),LocalDateTime.now(),code,expiresAt,status);
        MobileVerifyEmailResponse response = userVerifyMapper.mapTowebResponse(newRecord);
        return ResponseEntity.ok(response);
    }
}

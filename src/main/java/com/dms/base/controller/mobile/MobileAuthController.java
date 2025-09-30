package com.dms.base.controller.mobile;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import java.util.Map;
import com.dms.base.controller.common.AuthController;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.dms.base.exception.InvalidCredentialsException;
import com.dms.base.model.Address;
import com.dms.base.model.User;
import com.dms.base.exception.AccountLockedException;
import com.dms.base.dto.request.LoginRequest;
import com.dms.base.dto.request.mobile.MobileResestPasswordResetRequest;
import com.dms.base.dto.request.mobile.MobileRegisterRequest; 
import com.dms.base.dto.response.mobile.MobileRegisterResponse;
import com.dms.base.exception.UserAlreadyExistsException;
import com.dms.base.dto.request.mobile.MoblieVerifyEmailRequest;
import com.dms.base.dto.response.common.LoginResponse;

@RestController
@RequestMapping("/v1/mobile/auth")
@Tag(name = "Authentication API", description = "These services provide APIs related to authentication and session management for DMS Mobile Application.")
public class MobileAuthController extends AuthController {

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
    public ResponseEntity<?> verifyEmail(@RequestBody MoblieVerifyEmailRequest request) {
        User existUser = userService.getByUserName(request.getUserName());
        if (existUser == null) {
            return ResponseEntity.ok(null);
        }
        Address userAddress = addressService.findProfiledAddress(existUser.getId());
        if (userAddress != null && userAddress.getEmail() != null) {
            userVerifyService.verifyEmail(userAddress);
        }
        userVerifyService.verifyEmail(userAddress);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/resest-password")
    public ResponseEntity<?> resetPassword(@RequestBody MobileResestPasswordResetRequest request) {
        User existUser = userService.getByUserName(request.getUserName());
        if (existUser == null) {
            return ResponseEntity.ok(null);
        }
        authService.resetPassword(request.getUserName()); // we can add new password in request body and pass it later
        return ResponseEntity.ok(null);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        // Implementation here later
        return ResponseEntity.ok(null);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody MobileRegisterRequest request) {
        try {
            MobileRegisterResponse response = userService.registerNewMobileUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("error", "USER_ALREADY_EXISTS", "message", e.getMessage()));
        }
    }
}

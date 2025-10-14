package com.dms.base.controller.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.dms.base.controller.common.AuthController;
import com.dms.base.dto.request.LoginRequest;
import com.dms.base.dto.request.web.EnableTwoFactorAuthRequest;
import com.dms.base.dto.request.web.VerivyTwoFactorAuthRequest;
import com.dms.base.dto.response.web.WebLoginResponse;
import com.dms.base.dto.response.web.WebTwoFactorAuthResponse;
import com.dms.base.exception.AccountLockedException;
import com.dms.base.exception.BadRequestException;
import com.dms.base.exception.InvalidCredentialsException;
import com.dms.base.exception.ObjectNotFoundException;
import com.dms.base.mapper.UserMapper;
import com.dms.base.model.User;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/web/auth")
@Tag(name = "Web Authentication API", description = "These services provide APIs related to authentication and session management for DMS Web Portal.")
public class WebAuthController extends AuthController {
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            WebLoginResponse response = authService.webLogin(request.getUserName(), request.getPassword());
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

    @GetMapping("/2fa/setup")
    public ResponseEntity<?> setupTwoFactorAuth() {
        User loggedInUser = userService.getCurrentUser();
        WebTwoFactorAuthResponse response = twoFactorAuthService.setupTwoFactorAuth(loggedInUser);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/2fa/enable")
    public ResponseEntity<?> enableTwoFactorAuth(@RequestBody EnableTwoFactorAuthRequest request) {
        try {
            User loggedInUser = userService.getCurrentUser();

            if (request.getVerificationCode() == null || request.getVerificationCode().trim().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "Secret key is required"));
            }

            boolean isEnabled = twoFactorAuthService.enableTwoFactorAuth(loggedInUser, request.getVerificationCode());
            if (isEnabled) {
                return ResponseEntity.ok()
                        .body(Map.of(
                                "success", true,
                                "message", "Two-factor authentication enabled successfully"));
            } else {
                return ResponseEntity.badRequest()
                        .body(Map.of(
                                "success", false,
                                "error", "Failed to enable two-factor authentication"));
            }

        } catch (ObjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/2fa/disable")
    public ResponseEntity<?> disableTwoFactorAuth() {
        try {
            User loggedInUser = userService.getCurrentUser();
            boolean isDisabled = twoFactorAuthService.disableTwoFactorAuth(loggedInUser);

            if (isDisabled) {
                return ResponseEntity.ok()
                        .body(Map.of(
                                "success", true,
                                "message", "Two-factor authentication disabled successfully"));
            } else {
                return ResponseEntity.badRequest()
                        .body(Map.of(
                                "success", false,
                                "error", "Failed to disable two-factor authentication"));
            }

        } catch (ObjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "success", false,
                            "error", e.getMessage()));
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of(
                            "success", false,
                            "error", e.getMessage()));
        }
    }

    @PostMapping("/2fa/verify")
    public ResponseEntity<?> verifyTwoFactorAuth(@RequestBody VerivyTwoFactorAuthRequest request) {
        try {
            User user = userService.getByUserId(request.getUserId());
            if (request.getUserId() == null) {
                return ResponseEntity.badRequest()
                        .body(Map.of(
                                "success", false,
                                "error", "User ID is required"));
            }

            if (request.getVerificationCode() == null) {
                return ResponseEntity.badRequest()
                        .body(Map.of(
                                "success", false,
                                "error", "Secret key is required"));
            }

            boolean isValid = twoFactorAuthService.verifyTwoFactorAuth(request.getUserId(), request.getVerificationCode());
            if (isValid) {
                return ResponseEntity.ok(authService.webTwoFactorAuthLogin(user));
            } else {
                return ResponseEntity.badRequest()
                        .body(Map.of(
                                "success", false,
                                "error", "Invalid secret key",
                                "verified", false));
            }
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "success", false,
                            "error", e.getMessage()));
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of(
                            "success", false,
                            "error", e.getMessage()));
        }
    }
}

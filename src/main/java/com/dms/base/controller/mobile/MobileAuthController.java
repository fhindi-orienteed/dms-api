package com.dms.base.controller.mobile;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.Map;
import com.dms.base.controller.common.AuthController;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.dms.base.exception.InvalidCredentialsException;
import com.dms.base.exception.ObjectNotFoundException;
import com.dms.base.model.Address;
import com.dms.base.model.User;
import com.dms.base.model.UserVerify;
import com.dms.base.exception.AccountLockedException;
import com.dms.base.dto.request.LoginRequest;
import com.dms.base.dto.response.common.LoginResponse;
import com.dms.base.dto.response.mobile.MobileVerifyEmailResponse;

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
    public ResponseEntity<MobileVerifyEmailResponse> verifyEmail() {
        User currentUser = userService.getCurrentUser();
        Address userAddress = addressService.findByUserId(currentUser.getId());
        if (userAddress == null) {
            throw new ObjectNotFoundException("Address for user id " + currentUser.getId() + " not found");
        }
        UserVerify newRecord = userVerifyService.verifyEmail(userAddress);
        MobileVerifyEmailResponse response = userVerifyMapper.mapToMobileResponse(newRecord);
        return ResponseEntity.ok(response);
    }
}

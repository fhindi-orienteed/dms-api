package com.dms.base.controller.mobile;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dms.base.controller.common.UserController;
import com.dms.base.model.User;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/mobile/user")
@Tag(name = "Users API", description = "These services provide APIs related to logged in user DMS Mobile Application.")
public class MobileUserController extends UserController {

    @GetMapping("/current")
    public ResponseEntity<?> getCurrentUser() {
        User user = userService.getCurrentUser();
        return ResponseEntity.ok(userMapper.mapToMobileResponse(user));
    }
}

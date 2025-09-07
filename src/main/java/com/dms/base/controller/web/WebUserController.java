package com.dms.base.controller.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dms.base.controller.common.UserController;
import com.dms.base.model.User;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/web/users")
@Tag(name = "Web API", description = "Endpoints for Web Users API")
public class WebUserController extends UserController {

    @GetMapping("/current")
    public ResponseEntity<?> getCurrentUser() {
        User user = userService.getCurrentUser();
        return ResponseEntity.ok(userMapper.mapToWebResponse(user));
    }
}

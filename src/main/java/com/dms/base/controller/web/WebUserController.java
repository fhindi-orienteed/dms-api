package com.dms.base.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dms.base.controller.common.UserController;
import com.dms.base.dto.request.web.CreateNewUserRequest;
import com.dms.base.dto.response.web.WebDriverResponse;
import com.dms.base.dto.response.web.WebUserResponse;
import com.dms.base.exception.BadRequestException;
import com.dms.base.mapper.DriverMapper;
import com.dms.base.model.Driver;
import com.dms.base.model.User;
import com.dms.base.service.DriverService;
import com.dms.base.util.Constant.RoleType;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/web/users")
@Tag(name = "Users API", description = "Endpoints for Web Users API")
public class WebUserController extends UserController {

    @Autowired
    private DriverService driverService;

    @Autowired
    private DriverMapper driverMapper;

    @GetMapping("/current")
    public ResponseEntity<?> getCurrentUser() {
        User user = userService.getCurrentUser();
        return ResponseEntity.ok(userMapper.mapToWebResponse(user));
    }

    @PostMapping("/new")
    public ResponseEntity<?> createNewUser(@RequestBody CreateNewUserRequest request) {
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new BadRequestException("Passwords do not match");
        }

        User user = userService.createNewUser(request.getUserName(), request.getPassword(),
                request.getRole());

        if (user.getRole().equals(RoleType.ROLE_DRIVER.name())) {
            Driver driver = driverService.createNewDriver(user.getId());
            WebDriverResponse res = driverMapper.mapToWebResponse(driver, user);
            return ResponseEntity.ok(res);
        } else {
            WebUserResponse res = userMapper.mapToWebResponse(user);
            return ResponseEntity.ok(res);
        }
    }
}

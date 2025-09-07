package com.dms.base.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dms.base.controller.common.UserController;
import com.dms.base.dto.request.web.CreateNewUserRequest;
import com.dms.base.dto.response.web.WebCreateUserResponse;
import java.util.Date;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.dms.base.mapper.DriverMapper;
import com.dms.base.model.Driver;
import com.dms.base.model.User;
import com.dms.base.exception.BadRequestException;
import com.dms.base.service.DriverService;
import com.dms.base.dto.response.web.WebDriverResponse;
import com.dms.base.dto.response.web.WebUserResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/web/users")
@Tag(name = "Web API", description = "Endpoints for Web Users API")
public class WebUserController extends UserController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DriverService driverService;

    @Autowired
    private DriverMapper driverMapper;

    @PostMapping
    public ResponseEntity<WebCreateUserResponse> createNewUser(@RequestBody CreateNewUserRequest newUserRequest) {
        if (!newUserRequest.getPassword().equals(newUserRequest.getConfirmPassword())) {
            throw new BadRequestException("Passwords do not match");
        }

        String hashedPassword = passwordEncoder.encode(newUserRequest.getPassword());
        
        // Call the service method with individual parameters
        User userResponse = userService.createNewUser(newUserRequest.getUserName(),hashedPassword,newUserRequest.getRole(),new Date(), 1,  0,0,
            0,1800
        );
        Driver newDriver = new Driver();
        newDriver.setCompanyId(newUserRequest.getCompanyId());
        newDriver.setUserId(userResponse.getId());
        
        Driver responseDriver = driverService.createNewDriver(newDriver);

        // Map entities to response DTOs
        WebUserResponse userResponseDto = userMapper.mapToWebResponse(userResponse);
        WebDriverResponse driverResponseDto = driverMapper.mapToWebResponse(responseDriver);

        WebCreateUserResponse response = new WebCreateUserResponse();
        response.setUser(userResponseDto);
        response.setDriver(driverResponseDto);
        response.setMessage("Successfully Created");
        return ResponseEntity.ok(response);
    }
}
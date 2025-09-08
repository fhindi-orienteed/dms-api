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
@Tag(name = "Users API", description = "Endpoints for Web Users API")
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

    @GetMapping("/current")
    public ResponseEntity<?> getCurrentUser() {
        User user = userService.getCurrentUser();
        return ResponseEntity.ok(userMapper.mapToWebResponse(user));
    }
}

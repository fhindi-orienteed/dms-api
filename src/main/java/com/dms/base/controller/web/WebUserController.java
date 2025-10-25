package com.dms.base.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dms.base.dto.request.web.UpdateProfileRequest;
import com.dms.base.controller.common.UserController;
import com.dms.base.dto.response.web.WebUserProfileResponse;
import com.dms.base.exception.ObjectNotFoundException;
import com.dms.base.mapper.CompanyMapper;
import com.dms.base.mapper.DriverMapper;
import com.dms.base.model.Company;
import com.dms.base.model.CompanyUser;
import com.dms.base.model.Driver;
import com.dms.base.model.Profile;
import com.dms.base.model.User;
import com.dms.base.service.CompanyService;
import com.dms.base.service.CompanyUserService;
import com.dms.base.service.DriverService;
import com.dms.base.service.ProfileService;
import com.dms.base.util.Constant.RoleType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/web/user")
@Tag(name = "Users API", description = "Endpoints for Web Users API")
public class WebUserController extends UserController {

    @Autowired
    private ProfileService profileService;
    @Autowired
    private DriverService driverService;
    @Autowired
    private DriverMapper driverMapper;
    @Autowired
    private CompanyUserService companyUserService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyMapper companyMapper;

    @GetMapping("/current")
    public ResponseEntity<?> getCurrentUser() {
        User user = userService.getCurrentUser();
        return ResponseEntity.ok(userMapper.mapToWebResponse(user));
    }

    @GetMapping("/current/profile")
    @Operation(summary = "Get the current web user's full profile")
    public ResponseEntity<WebUserProfileResponse> getUserProfile() {
        return ResponseEntity.ok(profileService.buildUserProfileResponse());
    }

    @PostMapping("/profile/update")
    @Operation(summary = "Update the current web user's profile")
    public ResponseEntity<WebUserProfileResponse> updateProfile(@RequestBody UpdateProfileRequest request) {
    
        return ResponseEntity.ok(profileService.updateProfile(request));
    }
}

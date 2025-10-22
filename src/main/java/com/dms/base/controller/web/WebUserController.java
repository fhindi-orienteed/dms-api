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
import com.dms.base.dto.response.web.WebUserProfileResponse;
import com.dms.base.exception.BadRequestException;
import com.dms.base.exception.ObjectNotFoundException;
import com.dms.base.mapper.DriverMapper;
import com.dms.base.mapper.CompanyMapper;
import com.dms.base.model.Driver;
import com.dms.base.model.User;
import com.dms.base.model.Profile;
import com.dms.base.model.Company;
import com.dms.base.model.CompanyUser;
import com.dms.base.service.DriverService;
import com.dms.base.service.ProfileService;
import com.dms.base.service.CompanyService; 
import com.dms.base.service.CompanyUserService;
import com.dms.base.util.Constant.RoleType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/web/users")
@Tag(name = "Users API", description = "Endpoints for Web Users API")
public class WebUserController extends UserController {

    @Autowired
    private DriverService driverService;

    @Autowired
    private DriverMapper driverMapper;

    @Autowired
    private ProfileService profileService;
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

    @GetMapping("/profile")
    @Operation(summary = "Get the current web user's full profile")
    public ResponseEntity<WebUserProfileResponse> getUserProfile() {
        User user = userService.getCurrentUser();
        if (user == null) {
            throw new ObjectNotFoundException("No authenticated user found.");
        }

        Profile profile = profileService.findByUserId(user.getId());

        WebUserProfileResponse response = new WebUserProfileResponse();
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        
        if (profile != null) {
            response.setFirstName(profile.getFirstName());
            response.setLastName(profile.getLastName());
            response.setMobile(profile.getMobile());
            response.setAddress(profile.getAddress());
        }

        if (RoleType.ROLE_DRIVER.name().equals(user.getRole())) {
            Driver driver = driverService.findByUserId(user.getId());
            response.setDriverDetails(driverMapper.mapToWebResponse(driver)); 
        } else if (RoleType.ROLE_COMPANY_USER.name().equals(user.getRole()) || RoleType.ROLE_COMPANY_ADMIN.name().equals(user.getRole())) {
            CompanyUser companyUser = companyUserService.findByUserId(user.getId());
            if (companyUser != null) {
                Company company = companyService.findByCompanyId(companyUser.getCompanyId());
                response.setCompanyDetails(companyMapper.mapToWebResponse(company));
            }
        }

        return ResponseEntity.ok(response);
    }
}

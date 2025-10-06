package com.dms.base.controller.mobile;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dms.base.controller.common.UserController;
import com.dms.base.dto.request.mobile.MobilMerchantUserRegisterRequest;
import com.dms.base.dto.request.mobile.RegisterDeviceTokenRequest;
import com.dms.base.dto.response.mobile.MobileCompanyResponse;
import com.dms.base.dto.response.mobile.MobileDriverResponse;
import com.dms.base.dto.response.mobile.MobileRegisterResponse;
import com.dms.base.dto.response.mobile.MobileUserResponse;
import com.dms.base.exception.ObjectNotFoundException;
import com.dms.base.exception.UserAlreadyExistsException;
import com.dms.base.mapper.CompanyMapper;
import com.dms.base.mapper.DriverMapper;
import com.dms.base.model.Company;
import com.dms.base.model.CompanyUser;
import com.dms.base.model.Driver;
import com.dms.base.model.User;
import com.dms.base.service.CompanyService;
import com.dms.base.service.CompanyUserService;
import com.dms.base.service.DeviceTokenService;
import com.dms.base.service.DriverService;
import com.dms.base.util.Constant.RoleType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/mobile/user")
@Tag(name = "Users API", description = "These services provide APIs related to logged in user DMS Mobile Application.")
public class MobileUserController extends UserController {
    @Autowired
    private DriverService driverService;

    @Autowired
    private DriverMapper driverMapper;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private CompanyUserService companyUserService;

    @Autowired
    private DeviceTokenService deviceTokenService;

    @GetMapping("/current")
    public ResponseEntity<?> getCurrentUser() {
        User user = userService.getCurrentUser();
        return ResponseEntity.ok(userMapper.mapToMobileResponse(user));
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile() {
        User user = userService.getCurrentUser();
        MobileUserResponse userResponse = userMapper.mapToMobileResponse(user);

        Map<String, Object> response = new HashMap<>();
        response.put("user", userResponse);

        if (user.getRole().equals(RoleType.ROLE_DRIVER.name())) {
            Driver driver = driverService.findByUserId(user.getId());
            if (driver == null) {
                throw new ObjectNotFoundException("Driver with user Id " + user.getId() + " not Found");
            }
            MobileDriverResponse driverResponse = driverMapper.mapToMobileResponse(driver);
            response.put("driver", driverResponse);

        } else if (user.getRole().equals(RoleType.ROLE_COMPANY_USER.name())
                || user.getRole().equals(RoleType.ROLE_COMPANY_ADMIN.name())) {
            CompanyUser companyUser = companyUserService.findByUserId(user.getId());
            Company company = companyService.findByCompanyId(companyUser.getCompanyId());
            MobileCompanyResponse companyResponse = companyMapper.mapToMobileResponse(company);
            response.put("company", companyResponse);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/device-token")
    @Operation(summary = "Register Device Token", description = "Saves the mobile device's unique token for push notifications.")
    public ResponseEntity<Void> registerDeviceToken(@RequestBody RegisterDeviceTokenRequest request) {
        deviceTokenService.registerDeviceToken(request.getDeviceToken());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(MobilMerchantUserRegisterRequest request) {
        try {
            MobileRegisterResponse response = userService.registerNewMobileMerchantUser(request.getName(),
                    request.getEmail(), request.getPhone(), request.getPassword());
            return ResponseEntity.ok(response);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("error", "USER_ALREADY_EXISTS", "message", e.getMessage()));
        }
    }

}

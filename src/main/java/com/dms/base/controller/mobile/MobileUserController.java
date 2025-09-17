package com.dms.base.controller.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dms.base.controller.common.UserController;
import com.dms.base.dto.response.mobile.MobileCompanyResponse;
import com.dms.base.dto.response.mobile.MobileDriverResponse;
import com.dms.base.dto.response.web.WebDriverResponse;
import com.dms.base.exception.ObjectNotFoundException;
import com.dms.base.mapper.CompanyMapper;
import com.dms.base.mapper.DriverMapper;
import com.dms.base.model.Company;
import com.dms.base.model.Driver;
import com.dms.base.model.User;
import com.dms.base.service.CompanyService;
import com.dms.base.service.DriverService;
import com.dms.base.util.Constant.RoleType;
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

    @GetMapping("/current")
    public ResponseEntity<?> getCurrentUser() {
        User user = userService.getCurrentUser();
        return ResponseEntity.ok(userMapper.mapToMobileResponse(user));
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile() {
        User user = userService.getCurrentUser();
        if (user.getRole().equals(RoleType.ROLE_DRIVER.name())){
            Driver driver = driverService.getByUserId(user.getId());
            if (driver == null){
                throw new ObjectNotFoundException("Driver with user Id " + user.getId() +" not Found");
            }
            MobileDriverResponse res = driverMapper.mapToMobileResponse(driver,user);
            return ResponseEntity.ok(res);

        } else if (user.getRole().equals(RoleType.ROLE_COMPANY_USER.name()) || user.getRole().equals(RoleType.ROLE_COMPANY_ADMIN.name())) {
            Company company = companyService.getByUserId(user.getId());
            MobileCompanyResponse res = companyMapper.mapToMobileResponse(company, user);
            return ResponseEntity.ok(res);
        }

        return ResponseEntity.ok(userMapper.mapToMobileResponse(user));
        
    }

}
  
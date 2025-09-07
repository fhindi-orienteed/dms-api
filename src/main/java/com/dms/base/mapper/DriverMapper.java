package com.dms.base.mapper;

import com.dms.base.dto.request.web.CreateNewUserRequest;
import com.dms.base.model.Driver;
import org.springframework.stereotype.Component;

@Component
public class DriverMapper {
    public Driver mpaToCreateWebDriver(CreateNewUserRequest newUserRequest) {
        Driver driver = new Driver();
        driver.setCompanyId(newUserRequest.getCompanyId());
        driver.setUserId(newUserRequest.getCompanyId());
        return driver;
    }
}

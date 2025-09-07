package com.dms.base.mapper;

import com.dms.base.dto.response.web.WebDriverResponse;
import com.dms.base.model.Driver;
import org.springframework.stereotype.Component;

@Component
public class DriverMapper {
    public WebDriverResponse mapToWebResponse(Driver driver) {
        WebDriverResponse response = new WebDriverResponse();
        response.setId(driver.getId());
        response.setCompanyId(driver.getCompanyId());
        response.setUserId(driver.getUserId());
        return response;
    }
}

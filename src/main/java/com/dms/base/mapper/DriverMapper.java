package com.dms.base.mapper;

import org.springframework.stereotype.Component;

import com.dms.base.dto.response.mobile.MobileDriverResponse;
import com.dms.base.dto.response.web.WebDriverResponse;
import com.dms.base.model.Driver;
import com.dms.base.model.User;

@Component
public class DriverMapper {
    public WebDriverResponse mapToWebResponse(Driver driver) {
        WebDriverResponse response = new WebDriverResponse();
        response.setDriverId(driver.getId());
        // TODO: add other property
        return response;
    }

    public WebDriverResponse mapToWebResponse(Driver driver, User user) {
        WebDriverResponse response = mapToWebResponse(driver);

        response.setId(user.getId());
        response.setCreatedDate(user.getCreatedDate());
        // TODO: add other property
        return response;
    }
    
    public MobileDriverResponse mapToMobileResponse(Driver driver) {
        MobileDriverResponse response = new MobileDriverResponse();
        response.setDriverId(driver.getId());
        response.setName(driver.getName());
        response.setAddress(driver.getAddress());
        response.setMobile(driver.getMobile());
        response.setEmail(driver.getEmail());
        response.setRating(driver.getRating());
        return response;
    }
}

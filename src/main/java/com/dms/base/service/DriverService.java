package com.dms.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dms.base.model.Driver;
import com.dms.base.repository.DriverRepository;

@Service
public class DriverService {
    @Autowired
    private DriverRepository driverRepository;

    public Driver createNewDriver(Long userId) {
        Driver driver = new Driver();
        driver.setUserId(userId);
        return driverRepository.save(driver);
    }
}

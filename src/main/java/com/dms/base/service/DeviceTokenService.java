package com.dms.base.service;

import com.dms.base.model.DeviceToken;
import com.dms.base.model.User;
import com.dms.base.repository.DeviceTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class DeviceTokenService {

    private final DeviceTokenRepository deviceTokenRepository;
    private final UserService userService;

    @Autowired
    public DeviceTokenService(DeviceTokenRepository deviceTokenRepository, UserService userService) {
        this.deviceTokenRepository = deviceTokenRepository;
        this.userService = userService;
    }

    public void registerDeviceToken(String token) {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            throw new IllegalStateException("No authenticated user found to register device token.");
        }

        DeviceToken deviceToken = new DeviceToken();
        deviceToken.setUser(currentUser);
        deviceToken.setToken(token);
        deviceToken.setCreatedDate(LocalDateTime.now());

        deviceTokenRepository.save(deviceToken);
    }
}
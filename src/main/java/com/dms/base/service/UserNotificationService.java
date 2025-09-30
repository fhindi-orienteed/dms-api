package com.dms.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dms.base.repository.UserNotificationRepository;

@Service
public class UserNotificationService {
    @Autowired
    private UserNotificationRepository userNotificationRepository;
}

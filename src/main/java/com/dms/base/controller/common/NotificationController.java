package com.dms.base.controller.common;

import org.springframework.beans.factory.annotation.Autowired;

import com.dms.base.service.NotificationService;

public class NotificationController {
    @Autowired
    protected NotificationService notificationService;
}

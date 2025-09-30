package com.dms.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dms.base.model.Notification;
import com.dms.base.repository.NotificationRepository;

@Service
public class NotificationService {
    
    @Autowired
    private NotificationRepository notificationRepository;
    
    public Page<Notification> getAllNotifications(Pageable pageable) {
        return notificationRepository.findAll(pageable);
    }
}
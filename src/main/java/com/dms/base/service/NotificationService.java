package com.dms.base.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dms.base.model.Notification;
import com.dms.base.repository.NotificationRepository;
import com.dms.base.specification.NotificationSpecification;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public Page<Notification> getNotificationsByUserId(Long userId, Pageable pageable) {
        Specification<Notification> spec = NotificationSpecification.joinWithUserNotifications(userId)
            .and(NotificationSpecification.orderByCreatedAtDesc());
            
        return notificationRepository.findAll(spec , pageable);
    }
}
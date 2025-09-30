package com.dms.base.controller.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dms.base.controller.common.NotificationController;
import com.dms.base.model.Notification;
import org.springframework.data.domain.Sort;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/web/notification")
@Tag(name = "Notification API", description = "This class provides RESTful services to get, add, update, or delete notification used by DMS Web Portal")
public class WebNotificationController extends NotificationController {

    @GetMapping("/list")
    public ResponseEntity<Page<Notification>> listNotification(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Notification> notifications = notificationService.getAllNotifications(pageable);
        return ResponseEntity.ok(notifications);
    }
}
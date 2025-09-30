package com.dms.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dms.base.model.UserNotification;

@Repository
public interface UserNotificationRepository extends JpaRepository<UserNotification, Long> {

}

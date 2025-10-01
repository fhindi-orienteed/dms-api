package com.dms.base.specification;

import org.springframework.data.jpa.domain.Specification;

import com.dms.base.model.Notification;
import com.dms.base.model.UserNotification;

import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;

public class NotificationSpecification {
    public static Specification<Notification> joinWithUserNotifications(Long userId) {
        return (root, query, cb) -> {
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<UserNotification> userNotificationRoot = subquery.from(UserNotification.class);
            subquery.select(userNotificationRoot.get("notificationId"))
                    .where(cb.equal(userNotificationRoot.get("userId"), userId));
            return root.get("id").in(subquery);
        };
    }

    public static Specification<Notification> orderByCreatedAtDesc() {
        return (root, query, cb) -> {
            query.orderBy(cb.desc(root.get("createdAt")));
            return null;
        };
    }
}

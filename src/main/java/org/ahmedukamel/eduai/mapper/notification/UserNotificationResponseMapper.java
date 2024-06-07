package org.ahmedukamel.eduai.mapper.notification;

import org.ahmedukamel.eduai.dto.notification.UserNotificationResponse;
import org.ahmedukamel.eduai.model.NotificationDetail;
import org.ahmedukamel.eduai.model.UserNotification;
import org.ahmedukamel.eduai.util.notification.NotificationUtils;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserNotificationResponseMapper implements Function<UserNotification, UserNotificationResponse> {
    @Override
    public UserNotificationResponse apply(UserNotification userNotification) {
        NotificationDetail notificationDetail = NotificationUtils.getNotificationDetail(userNotification.getNotification());

        return new UserNotificationResponse(
                userNotification.getNotification().getId(),
                notificationDetail.getMessage(),
                userNotification.getNotification().getCreatedAt(),
                userNotification.isRead()
        );
    }
}
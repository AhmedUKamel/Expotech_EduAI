package org.ahmedukamel.eduai.mapper.notification;

import org.ahmedukamel.eduai.dto.notification.NotificationResponse;
import org.ahmedukamel.eduai.model.Notification;
import org.ahmedukamel.eduai.model.NotificationDetail;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.util.notification.NotificationUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class NotificationResponseMapper implements Function<Notification, NotificationResponse> {
    @Override
    public NotificationResponse apply(Notification notification) {
        NotificationDetail notificationDetail_en = NotificationUtils
                .getNotificationDetail(notification, Language.ENGLISH),

                notificationDetail_ar = NotificationUtils
                        .getNotificationDetail(notification, Language.ARABIC),

                notificationDetail_fr = NotificationUtils
                        .getNotificationDetail(notification, Language.FRENCH);

        List<Long> usersId = notification.getUserNotifications()
                .stream()
                .map(userNotification -> userNotification.getUser().getId())
                .toList();

        return new NotificationResponse(
                notification.getId(),
                notificationDetail_en.getMessage(),
                notificationDetail_ar.getMessage(),
                notificationDetail_fr.getMessage(),
                notification.getCreatedAt(),
                usersId
        );
    }
}
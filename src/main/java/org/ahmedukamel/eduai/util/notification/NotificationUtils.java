package org.ahmedukamel.eduai.util.notification;

import org.ahmedukamel.eduai.model.Notification;
import org.ahmedukamel.eduai.model.NotificationDetail;
import org.ahmedukamel.eduai.model.User;
import org.ahmedukamel.eduai.model.UserNotification;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.springframework.context.i18n.LocaleContextHolder;

public class NotificationUtils {
    public static NotificationDetail getNotificationDetail(Notification notification) {
        return notification.getDetails()
                .stream()
                .filter(notificationDetail -> notificationDetail.getLanguage().getCode()
                        .equalsIgnoreCase(LocaleContextHolder.getLocale().getLanguage()))
                .findFirst()
                .orElseThrow();
    }

    public static NotificationDetail getNotificationDetail(Notification notification, Language language) {
        return notification.getDetails()
                .stream()
                .filter(notificationDetail -> notificationDetail.getLanguage().equals(language))
                .findFirst()
                .orElseThrow();
    }

    public static UserNotification getUserNotification(User user, Notification notification) {
        return notification.getUserNotifications()
                .stream()
                .filter(userNotification -> userNotification.getUser().getId().equals(user.getId()))
                .findFirst()
                .orElseThrow();
    }
}
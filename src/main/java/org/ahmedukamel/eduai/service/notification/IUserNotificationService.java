package org.ahmedukamel.eduai.service.notification;

public interface IUserNotificationService {
    Object getNotification(Long id);

    Object readNotification(Long id);

    Object getAllNotifications(int pageSize, int pageNumber);
}
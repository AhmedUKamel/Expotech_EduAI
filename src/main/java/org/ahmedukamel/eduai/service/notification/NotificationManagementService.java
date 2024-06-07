package org.ahmedukamel.eduai.service.notification;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.notification.CreateNotificationRequest;
import org.ahmedukamel.eduai.dto.notification.NotificationResponse;
import org.ahmedukamel.eduai.mapper.notification.NotificationResponseMapper;
import org.ahmedukamel.eduai.model.Notification;
import org.ahmedukamel.eduai.saver.notification.NotificationSaver;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationManagementService implements INotificationManagementService {
    private final NotificationResponseMapper notificationResponseMapper;
    private final NotificationSaver notificationSaver;

    @Transactional
    @Override
    public Object createNotification(Object object) {
        CreateNotificationRequest request = (CreateNotificationRequest) object;

        Notification savedNotification = notificationSaver.apply(request);

        NotificationResponse response = notificationResponseMapper.apply(savedNotification);
        String message = "Notification created and sent successfully";

        return new ApiResponse(true, message, response);
    }
}
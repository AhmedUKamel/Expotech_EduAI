package org.ahmedukamel.eduai.service.notification;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.notification.UserNotificationResponse;
import org.ahmedukamel.eduai.mapper.notification.UserNotificationResponseMapper;
import org.ahmedukamel.eduai.model.Notification;
import org.ahmedukamel.eduai.model.User;
import org.ahmedukamel.eduai.model.UserNotification;
import org.ahmedukamel.eduai.repository.NotificationRepository;
import org.ahmedukamel.eduai.repository.UserNotificationRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.util.context.ContextHolderUtils;
import org.ahmedukamel.eduai.util.notification.NotificationUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserNotificationService implements IUserNotificationService {
    private final UserNotificationResponseMapper userNotificationResponseMapper;
    private final UserNotificationRepository userNotificationRepository;
    private final NotificationRepository notificationRepository;

    @Override
    public Object getNotification(Long id) {
        User user = ContextHolderUtils.getUser();
        Notification notification = DatabaseService.get(notificationRepository::findById, id, Notification.class);

        UserNotification userNotification = NotificationUtils.getUserNotification(user, notification);

        UserNotificationResponse response = userNotificationResponseMapper.apply(userNotification);
        String message = "Notification retrieved successfully";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object readNotification(Long id) {
        User user = ContextHolderUtils.getUser();
        Notification notification = DatabaseService.get(notificationRepository::findById, id, Notification.class);

        UserNotification userNotification = NotificationUtils.getUserNotification(user, notification);
        userNotification.setRead(true);

        userNotificationRepository.save(userNotification);

        UserNotificationResponse response = userNotificationResponseMapper.apply(userNotification);
        String message = "Notification read successfully";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getAllNotifications(int pageSize, int pageNumber) {
        User user = ContextHolderUtils.getUser();

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);

        Page<UserNotification> userNotifications = userNotificationRepository
                .findAllByUser_Id(user.getId(), pageable);

        Page<UserNotificationResponse> response = userNotifications
                .map(userNotificationResponseMapper);
        String message = "Notifications retrieved successfully";

        return new ApiResponse(true, message, response);
    }
}
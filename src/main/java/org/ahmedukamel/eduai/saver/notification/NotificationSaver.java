package org.ahmedukamel.eduai.saver.notification;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.notification.CreateNotificationRequest;
import org.ahmedukamel.eduai.model.Notification;
import org.ahmedukamel.eduai.model.NotificationDetail;
import org.ahmedukamel.eduai.model.UserNotification;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.repository.NotificationRepository;
import org.ahmedukamel.eduai.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class NotificationSaver implements Function<CreateNotificationRequest, Notification> {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Override
    public Notification apply(CreateNotificationRequest request) {
        Notification notification = new Notification();

        Set<UserNotification> userNotifications = request.userIds()
                .stream()
                .flatMap(Stream::ofNullable)
                .map(userRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(user -> UserNotification
                        .builder()
                        .user(user)
                        .notification(notification)
                        .build())
                .collect(Collectors.toSet());

        NotificationDetail notificationDetail_en = NotificationDetail
                .builder()
                .notification(notification)
                .language(Language.ENGLISH)
                .message(request.message_en().strip())
                .build(),

                notificationDetail_ar = NotificationDetail
                        .builder()
                        .notification(notification)
                        .language(Language.ARABIC)
                        .message(request.message_ar().strip())
                        .build(),

                notificationDetail_fr = NotificationDetail
                        .builder()
                        .notification(notification)
                        .language(Language.FRENCH)
                        .message(request.message_fr().strip())
                        .build();

        notification.setUserNotifications(userNotifications);
        notification.setDetails(Set.of(notificationDetail_en, notificationDetail_ar, notificationDetail_fr));

        return notificationRepository.save(notification);
    }
}
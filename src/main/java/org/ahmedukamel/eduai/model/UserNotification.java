package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_NOTIFICATIONS")
@IdClass(value = UserNotification.UserNotificationId.class)
public class UserNotification {
    @Id
    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private Notification notification;

    @Id
    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private User user;

    @Column(name = "is_read", nullable = false, columnDefinition = "bit(1) default false")
    private boolean read;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserNotificationId implements Serializable {
        private Notification notification;
        private User user;
    }
}
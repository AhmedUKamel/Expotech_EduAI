package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.*;
import org.ahmedukamel.eduai.model.enumeration.Language;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "NOTIFICATION_DETAILS")
@IdClass(value = NotificationDetail.NotificationDetailId.class)
public class NotificationDetail {
    @Id
    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private Notification notification;

    @Id
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private Language language;

    @Column(nullable = false, updatable = false)
    private String message;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NotificationDetailId implements Serializable {
        private Notification notification;
        private Language language;
    }
}
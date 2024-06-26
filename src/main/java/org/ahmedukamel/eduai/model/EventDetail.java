package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.*;
import org.ahmedukamel.eduai.model.enumeration.Language;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EVENT_DETAILS")
@IdClass(value = EventDetail.EventDetailId.class)
public class EventDetail {
    @Id
    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private Event event;

    @Id
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private Language language;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String filePath;

    @Transient
    private boolean active;
    public boolean getActive(){
        return (event.getEventStartDate().isBefore(LocalDateTime.now()) && event.getEventEndDate().isAfter(LocalDateTime.now()));
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EventDetailId implements Serializable {
        private Event event;
        private Language language;
    }
}
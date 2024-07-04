package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EVENTS")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private LocalDateTime eventStartTime;

    @Column(nullable = false)
    private LocalDateTime eventEndTime;

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private School school;

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private User creator;

    @Column
    private String file;

    @Column
    private boolean deleted = false;

    @Transient
    private boolean active;
    public boolean getActive(){
        return (eventStartTime.isBefore(LocalDateTime.now()) && eventEndTime.isAfter(LocalDateTime.now()));
    }

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "event_attendees",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "attendee_id"))
    private Set<User> attendees;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<EventDetail> details;
}
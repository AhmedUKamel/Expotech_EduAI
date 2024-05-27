package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.*;
import org.ahmedukamel.eduai.model.enumeration.RoomCategory;
import org.ahmedukamel.eduai.model.enumeration.RoomStatus;
import org.ahmedukamel.eduai.model.enumeration.RoomType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ROOMS", uniqueConstraints = {
        @UniqueConstraint(name = "ROOM_NUMBER_UNIQUE_CONSTRAINT", columnNames = {"room_number", "school_id"})
})
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String buildingNumber;

    @Column(nullable = false)
    private String roomNumber;

    @Column(nullable = false)
    private Integer floorNumber;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private RoomType type;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private RoomCategory category;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private RoomStatus status;

    @Column(nullable = false)
    private Integer maxCapacity;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "school_id", nullable = false, updatable = false)
    private School school;

    @OneToOne(mappedBy = "room", orphanRemoval = true)
    private Classroom classroom;

    @OneToOne(mappedBy = "room", orphanRemoval = true)
    private Lab lab;

    @OneToOne(mappedBy = "room", orphanRemoval = true)
    private Office office;
}
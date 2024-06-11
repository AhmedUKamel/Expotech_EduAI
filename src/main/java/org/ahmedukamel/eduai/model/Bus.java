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
@Table(name = "BUSES", uniqueConstraints = {
        @UniqueConstraint(name = "BUS_NUMBER_UNIQUE_CONSTRAINT", columnNames = {"bus_number", "school_id"})
})
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String busNumber;

    @Column(nullable = false)
    private String driverName;

    @Column(nullable = false)
    private String driverNumber;

    @Column(nullable = false)
    private Integer busCapacity;

    @Column(nullable = false)
    private String route;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "school_id", nullable = false, updatable = false)
    private School school;

}
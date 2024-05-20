package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CLASSES", uniqueConstraints = {
        @UniqueConstraint(name = "CLASS_NAME_UNIQUE_CONSTRAINT", columnNames = {"school_id", "name"}),
        @UniqueConstraint(name = "CLASS_NUMBER_UNIQUE_CONSTRAINT", columnNames = {"school_id", "number"}),
        @UniqueConstraint(name = "CLASS_GROUP_UNIQUE_CONSTRAINT", columnNames = {"school_id", "the_group"})
})
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String number;

    @Column(name = "the_group", nullable = false)
    private String group;

    @Column(nullable = false)
    private Integer floor;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "school_id", nullable = false, updatable = false)
    private School school;

    @OneToMany(mappedBy = "theClass")
    private Collection<Section> sections = new ArrayList<>();
}
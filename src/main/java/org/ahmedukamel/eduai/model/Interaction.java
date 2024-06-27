package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.*;
import org.ahmedukamel.eduai.model.enumeration.InteractionType;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "INTERACTIONS")
public class Interaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private String description;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime creationDate;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private InteractionType type;

    @ManyToOne
    @JoinColumn(updatable = false)
    private Parent parent;

    @ManyToOne
    @JoinColumn(updatable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(updatable = false)
    private Teacher teacher;
}
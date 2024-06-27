package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.*;
import org.ahmedukamel.eduai.model.enumeration.StudyLevel;
import org.ahmedukamel.eduai.model.enumeration.StudyStage;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CLASSROOMS")
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer number;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private StudyLevel level;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private StudyStage stage;

    @OneToOne
    @JoinColumn(nullable = false, updatable = false)
    private Room room;
}
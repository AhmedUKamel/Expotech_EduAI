package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.*;
import org.ahmedukamel.eduai.model.enumeration.LabType;
import org.ahmedukamel.eduai.model.enumeration.StudyLevel;
import org.ahmedukamel.eduai.model.enumeration.StudyStage;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LABS")
public class Lab {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private LabType type;

    @OneToOne
    @JoinColumn(nullable = false, updatable = false)
    private Room room;
}
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
@Table(name = "POSITION_DETAILS")
@IdClass(value = PositionDetails.PositionDetailsId.class)
public class PositionDetails {
    @Id
    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private Position position;

    @Id
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private Language language;

    @Column(nullable = false)
    private String title;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PositionDetailsId implements Serializable {
        private Position position;
        private Language language;
    }
}
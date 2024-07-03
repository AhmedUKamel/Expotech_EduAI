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
@Table(name = "DEPARTMENT_DETAILS")
@IdClass(value = DepartmentDetails.DepartmentDetailsId.class)
public class DepartmentDetails {
    @Id
    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private Department department;

    @Id
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private Language language;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String abbreviation;

    @Column(nullable = false)
    private String description;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DepartmentDetailsId implements Serializable {
        private Department department;
        private Language language;
    }
}
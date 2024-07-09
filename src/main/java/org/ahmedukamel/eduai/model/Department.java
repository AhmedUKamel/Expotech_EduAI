package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.*;
import org.ahmedukamel.eduai.model.enumeration.EmployeeRole;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DEPARTMENTS")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @OneToOne
    @JoinColumn(nullable = false)
    private Employee head;

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private School school;

    @ElementCollection(fetch = FetchType.EAGER, targetClass = EmployeeRole.class)
    @CollectionTable(name = "DEPARTMENT_ROLES")
    @Enumerated(value = EnumType.STRING)
    private Set<EmployeeRole> roles = new HashSet<>();

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<DepartmentDetails> details = new HashSet<>();

    @OneToMany(mappedBy = "department", orphanRemoval = true)
    private Set<Position> positions = new HashSet<>();
}
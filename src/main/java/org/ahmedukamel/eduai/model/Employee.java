package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.*;
import org.ahmedukamel.eduai.model.embeddable.PhoneNumber;
import org.ahmedukamel.eduai.model.enumeration.EmployeeRole;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EMPLOYEES", uniqueConstraints = {
        @UniqueConstraint(name = "EMPLOYEE_PHONE_NUMBER_UNIQUE_CONSTRAINT", columnNames = {"code", "number"})
})
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "countryCode", column = @Column(name = "code", nullable = false)),
            @AttributeOverride(name = "nationalNumber", column = @Column(name = "number", nullable = false))
    })
    private PhoneNumber phoneNumber;

    @Temporal(value = TemporalType.DATE)
    @Column(updatable = false)
    private LocalDate hireDate;

    private Double salary;

    @ManyToOne
    private Position position;

    @OneToOne
    @JoinColumn(nullable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private School school;

    @ElementCollection(fetch = FetchType.EAGER, targetClass = EmployeeRole.class)
    @CollectionTable(name = "EMPLOYEE_ROLES")
    @Enumerated(value = EnumType.STRING)
    private Set<EmployeeRole> roles = new HashSet<>();
}
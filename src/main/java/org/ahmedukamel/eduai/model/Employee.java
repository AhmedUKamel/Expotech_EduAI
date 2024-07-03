package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ahmedukamel.eduai.model.embeddable.PhoneNumber;
import org.ahmedukamel.eduai.model.enumeration.EmployeeRole;
import org.ahmedukamel.eduai.model.enumeration.EmployeeStatus;
import org.ahmedukamel.eduai.model.enumeration.EmployeeType;
import org.ahmedukamel.eduai.model.enumeration.Qualification;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EMPLOYEES", uniqueConstraints = {
        @UniqueConstraint(name = "EMPLOYEE_PHONE_NUMBER_UNIQUE_CONSTRAINT", columnNames = {"code", "number"})
})
public class Employee extends User {
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

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedDate;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private EmployeeStatus employeeStatus;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private EmployeeType employeeType;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Qualification qualification;

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private School school;

    @ElementCollection(fetch = FetchType.EAGER, targetClass = EmployeeRole.class)
    @CollectionTable(name = "EMPLOYEE_ROLES")
    @Enumerated(value = EnumType.STRING)
    private Set<EmployeeRole> employeeRoles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(this.getRole());
        authorities.addAll(this.employeeRoles);
        return authorities;
    }
}
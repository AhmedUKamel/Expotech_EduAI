package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.*;
import org.ahmedukamel.eduai.model.embeddable.PhoneNumber;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "TEACHERS", uniqueConstraints = {
        @UniqueConstraint(name = "TEACHER_PHONE_NUMBER_UNIQUE_CONSTRAINT", columnNames = {"code", "number"})
})
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(value = TemporalType.DATE)
    @Column(nullable = false, updatable = false)
    private LocalDate birthDate;

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "countryCode", column = @Column(name = "code", nullable = false)),
            @AttributeOverride(name = "nationalNumber", column = @Column(name = "number", nullable = false))
    })
    private PhoneNumber phoneNumber;

    @OneToOne
    @JoinColumn(nullable = false, updatable = false)
    private User user;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<TeacherDetail> details = new HashSet<>();
}
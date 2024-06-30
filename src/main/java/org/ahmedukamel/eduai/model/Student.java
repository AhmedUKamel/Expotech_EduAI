package org.ahmedukamel.eduai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.ahmedukamel.eduai.model.enumeration.Gender;
import org.ahmedukamel.eduai.model.enumeration.Religion;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "STUDENTS")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(nullable = false, updatable = false)
    private User user;

    @ManyToOne
    private Section section;

    @OneToMany(mappedBy = "student")
    private Collection<ExamResult> examResults;

    @OneToMany(mappedBy = "student")
    private Collection<Interaction> interactions;

    @OneToMany(mappedBy = "student")
    private Set<Attendance> attendances;

    @ManyToOne
    private Parent parent;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonIgnore
    private Grade grade;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private String fatherName;

    @Column(nullable = false)
    private String motherName;

    @Column(nullable = false)
    private Date dateOfBirth;

    @Column(nullable = false)
    private Religion religion;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String mobile;

}
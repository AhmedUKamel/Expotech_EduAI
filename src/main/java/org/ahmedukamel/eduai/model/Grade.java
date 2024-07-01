package org.ahmedukamel.eduai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="GRADES")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

//    @OneToMany
//    @JoinColumn(nullable = false, updatable = false)
//    private List<Student> students;

    @OneToMany
    @JoinColumn(nullable = false, updatable = false)
    private List<Course> courses;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonIgnore
    private School school;
}

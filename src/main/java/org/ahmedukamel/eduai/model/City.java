package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "CITIES", uniqueConstraints = {
        @UniqueConstraint(name = "CITY_ENGLISH_NAME_UNIQUE_CONSTRAINT", columnNames = "name_en"),
        @UniqueConstraint(name = "CITY_ARABIC_NAME_UNIQUE_CONSTRAINT", columnNames = "name_ar"),
        @UniqueConstraint(name = "CITY_FRENCH_NAME_UNIQUE_CONSTRAINT", columnNames = "name_fr")
})
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name_en;

    @Column(nullable = false)
    private String name_ar;

    @Column(nullable = false)
    private String name_fr;

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private Country country;

    @OneToMany(mappedBy = "city")
    private Set<Region> regions = new HashSet<>();
}
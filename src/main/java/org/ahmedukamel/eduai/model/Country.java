package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.ahmedukamel.eduai.util.address.INameLocalization;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "COUNTRIES", uniqueConstraints = {
        @UniqueConstraint(name = "COUNTRY_ENGLISH_NAME_UNIQUE_CONSTRAINT", columnNames = "name_en"),
        @UniqueConstraint(name = "COUNTRY_ARABIC_NAME_UNIQUE_CONSTRAINT", columnNames = "name_ar"),
        @UniqueConstraint(name = "COUNTRY_FRENCH_NAME_UNIQUE_CONSTRAINT", columnNames = "name_fr")
})
public class Country implements INameLocalization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name_en;

    @Column(nullable = false)
    private String name_ar;

    @Column(nullable = false)
    private String name_fr;

    @OneToMany(mappedBy = "country")
    private Set<City> cities = new HashSet<>();
}
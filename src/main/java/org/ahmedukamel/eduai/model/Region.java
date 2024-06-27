package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.ahmedukamel.eduai.model.embeddable.Location;
import org.ahmedukamel.eduai.util.address.INameLocalization;

@Getter
@Setter
@Entity
@Table(name = "REGIONS", uniqueConstraints = {
        @UniqueConstraint(name = "REGION_ENGLISH_NAME_UNIQUE_CONSTRAINT", columnNames = "name_en"),
        @UniqueConstraint(name = "REGION_ARABIC_NAME_UNIQUE_CONSTRAINT", columnNames = "name_ar"),
        @UniqueConstraint(name = "REGION_FRENCH_NAME_UNIQUE_CONSTRAINT", columnNames = "name_fr"),
        @UniqueConstraint(name = "REGION_LOCATION_UNIQUE_CONSTRAINT", columnNames = {"latitude", "longitude"})
})
public class Region implements INameLocalization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name_en;

    @Column(nullable = false)
    private String name_ar;

    @Column(nullable = false)
    private String name_fr;

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "latitude", column = @Column(nullable = false)),
            @AttributeOverride(name = "longitude", column = @Column(nullable = false))
    })
    private Location location;

    @Column(nullable = false)
    private Integer zipCode;

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private City city;
}
package org.ahmedukamel.eduai;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.model.City;
import org.ahmedukamel.eduai.model.Country;
import org.ahmedukamel.eduai.model.Region;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.model.embeddable.Location;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.repository.CityRepository;
import org.ahmedukamel.eduai.repository.CountryRepository;
import org.ahmedukamel.eduai.repository.RegionRepository;
import org.ahmedukamel.eduai.repository.SchoolRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class CommandLineRunnerBean {
    private final CountryRepository countryRepository;
    private final RegionRepository regionRepository;
    private final SchoolRepository schoolRepository;
    private final CityRepository cityRepository;

    void insertRegion() {
        if (regionRepository.count() == 0) {
            Country country = new Country();
            country.setName_en("Name En");
            country.setName_ar("Name Ar");
            country.setName_fr("Name Fr");
            Country savedCountry = countryRepository.save(country);

            City city = new City();
            city.setName_en("Name En");
            city.setName_ar("Name Ar");
            city.setName_fr("Name Fr");
            city.setCountry(savedCountry);
            City savedCity = cityRepository.save(city);

            Region region = new Region();
            region.setName_en("Name En");
            region.setName_ar("Name Ar");
            region.setName_fr("Name Fr");
            region.setCity(savedCity);
            region.setLocation(new Location(12.5, 12.5));
            region.setZipCode(123465);
            regionRepository.save(region);
        }
    }

    void insertSchool() {
        if (schoolRepository.count() == 0) {
            School school = new School();
            school.setTheme("Theme");
            school.setCode("Code");
            school.setName("Name");
            school.setAbout("About");
            school.setLanguage(Language.ENGLISH);
            school.setEstablished(LocalDate.of(2000, 1, 1));
            schoolRepository.save(school);
        }
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            insertRegion();
            insertSchool();
        };
    }
}
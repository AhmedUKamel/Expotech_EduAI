package org.ahmedukamel.eduai;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.model.*;
import org.ahmedukamel.eduai.model.embeddable.Location;
import org.ahmedukamel.eduai.model.enumeration.*;
import org.ahmedukamel.eduai.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CommandLineRunnerBean {
    private final CountryRepository countryRepository;
    private final RegionRepository regionRepository;
    private final SchoolRepository schoolRepository;
    private final CityRepository cityRepository;
    private final UserRepository userRepository;


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

    void insertUser(){
        if(userRepository.count() == 0){
            User user = new User();
            user.setUsername("admin");
            user.setEmail("mostafa@mostafa");
            user.setPassword("admin");
            user.setNid("123565449");
            user.setAbout("About");
            user.setPicture("picture");
            user.setAccountNonLocked(true);
            user.setEnabled(true);
            user.setGender(Gender.MALE);
            user.setRole(Role.TEACHER);
            user.setNationality(Nationality.EGYPTIAN);
            user.setReligion(Religion.MUSLIM);
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());
            user.setRegion(regionRepository.findById(1).get());
            user.setBirthDate(LocalDate.now());
            userRepository.save(user);
        }
    }
    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            insertRegion();
            insertSchool();
            insertUser();
        };
    }
}
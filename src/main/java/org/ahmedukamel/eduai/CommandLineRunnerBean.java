package org.ahmedukamel.eduai;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.employee.EmployeeRegistrationRequest;
import org.ahmedukamel.eduai.model.*;
import org.ahmedukamel.eduai.model.embeddable.Location;
import org.ahmedukamel.eduai.model.enumeration.*;
import org.ahmedukamel.eduai.repository.*;
import org.ahmedukamel.eduai.saver.employee.EmployeeRegistrationRequestSaver;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CommandLineRunnerBean {
    private final EmployeeRegistrationRequestSaver employeeRegistrationRequestSaver;
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;
    private final CountryRepository countryRepository;
    private final RegionRepository regionRepository;
    private final SchoolRepository schoolRepository;
    private final CityRepository cityRepository;
    private final UserRepository userRepository;

    private final EmployeeRegistrationRequest employeeRegistrationRequest = new EmployeeRegistrationRequest(
            "UniqueEmployeeUsername",
            "UniqueEmployeeEmail@domain.com",
            "Password$404",
            "0123456789",
            Religion.MUSLIM,
            Gender.MALE,
            Nationality.EGYPTIAN,
            1,
            LocalDate.of(2000, 1, 1),
            "0123465789",
            "English Name",
            "Arabic Name",
            "French Name",
            "About",
            1,
            EmployeeType.EMPLOYEE,
            Qualification.MASTER_DEGREE
    );


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
            School savedSchool = schoolRepository.save(school);

            Employee savedEmployee = employeeRegistrationRequestSaver.apply(employeeRegistrationRequest);

            Department department = new Department();
            department.setSchool(savedSchool);
            department.setHead(savedEmployee);
            department.setDetails(Set.of(
                    new DepartmentDetails(
                            department,
                            Language.ENGLISH,
                            "English Name",
                            "English Abbreviation",
                            "English Description"
                    ),

                    new DepartmentDetails(
                            department,
                            Language.ARABIC,
                            "Arabic Name",
                            "Arabic Abbreviation",
                            "Arabic Description"
                    ),


                    new DepartmentDetails(
                            department,
                            Language.FRENCH,
                            "French Name",
                            "French Abbreviation",
                            "French Description"
                    )
            ));

            Department savedDepartment = departmentRepository.save(department);

            Position position = new Position();
            position.setDepartment(savedDepartment);
            position.setDetails(Set.of(
                    new PositionDetails(
                            position,
                            Language.ENGLISH,
                            "English Title"
                    ),

                    new PositionDetails(
                            position,
                            Language.ARABIC,
                            "Arabic Title"
                    ),

                    new PositionDetails(
                            position,
                            Language.FRENCH,
                            "French Title"
                    )
            ));
            positionRepository.save(position);
        }
    }

    void insertUser() {
        if (userRepository.count() == 0) {
            User user = new User();
            user.setUsername("admin");
            user.setEmail("mostafa@mostafa");
            user.setPassword("admin"); // Should be encoded
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
            user.setRegion(regionRepository.findById(1).orElseThrow());
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
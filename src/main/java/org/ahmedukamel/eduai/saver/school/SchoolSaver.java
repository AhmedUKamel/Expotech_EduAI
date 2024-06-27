package org.ahmedukamel.eduai.saver.school;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.school.CreateSchoolRequest;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.repository.SchoolRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class SchoolSaver implements Function<CreateSchoolRequest, School> {
    private final SchoolRepository repository;

    @Override
    public School apply(CreateSchoolRequest request) {
        DatabaseService.unique(repository::existsByCodeIgnoreCase, request.code().strip(), School.class);

        DatabaseService.unique(repository::existsByNameIgnoreCase, request.name().strip(), School.class);

        School school = new School();

        school.setName(request.name().strip());
        school.setEstablished(request.established());
        school.setAbout(request.about().strip());
        school.setLanguage(request.language());
        school.setCode(request.code().strip().toUpperCase());
        school.setTheme(request.theme().strip());

        return repository.save(school);
    }
}
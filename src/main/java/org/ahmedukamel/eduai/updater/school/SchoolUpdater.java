package org.ahmedukamel.eduai.updater.school;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.school.UpdateSchoolRequest;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.repository.SchoolRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class SchoolUpdater implements BiFunction<School, UpdateSchoolRequest, School> {
    private final SchoolRepository repository;

    @Override
    public School apply(School school, UpdateSchoolRequest request) {
        if (!school.getName().equalsIgnoreCase(request.name().strip())) {
            DatabaseService.unique(repository::existsByNameIgnoreCase, request.name().strip(), School.class);
        }

        school.setName(request.name().strip());
        school.setAbout(request.about().strip());
        school.setLanguage(request.language());
        school.setTheme(request.theme().strip());

        return repository.save(school);
    }
}
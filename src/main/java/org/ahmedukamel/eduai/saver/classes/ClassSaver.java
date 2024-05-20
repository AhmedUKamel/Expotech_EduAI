package org.ahmedukamel.eduai.saver.classes;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.classes.CreateClassRequest;
import org.ahmedukamel.eduai.model.Class;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.repository.ClassRepository;
import org.ahmedukamel.eduai.repository.SchoolRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ClassSaver implements Function<CreateClassRequest, Class> {
    private final ClassRepository classRepository;
    private final SchoolRepository schoolRepository;

    @Override
    public Class apply(CreateClassRequest request) {
        Class theClass = new Class();

        theClass.setName(request.name().strip());
        theClass.setNumber(request.number().strip());
        theClass.setGroup(request.group().strip());
        theClass.setFloor(request.floor());
        theClass.setSchool(DatabaseService.get(schoolRepository::findById, request.schoolId(), School.class));

        return classRepository.save(theClass);
    }
}
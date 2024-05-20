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
    private final SchoolRepository schoolRepository;
    private final ClassRepository classRepository;

    @Override
    public Class apply(CreateClassRequest request) {
        DatabaseService.unique(classRepository::existsBySchool_IdAndGroupIgnoreCase,
                request.schoolId(), request.group().strip(), Class.class);

        DatabaseService.unique(classRepository::existsBySchool_IdAndNameIgnoreCase,
                request.schoolId(), request.name().strip(), Class.class);

        DatabaseService.unique(classRepository::existsBySchool_IdAndNumberIgnoreCase,
                request.schoolId(), request.number().strip(), Class.class);

        Class theClass = new Class();

        theClass.setName(request.name().strip());
        theClass.setNumber(request.number().strip());
        theClass.setGroup(request.group().strip());
        theClass.setFloor(request.floor());
        theClass.setSchool(DatabaseService.get(schoolRepository::findById, request.schoolId(), School.class));

        return classRepository.save(theClass);
    }
}
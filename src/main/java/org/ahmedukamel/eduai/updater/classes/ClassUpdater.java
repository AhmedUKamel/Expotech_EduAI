package org.ahmedukamel.eduai.updater.classes;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.classes.UpdateClassRequest;
import org.ahmedukamel.eduai.model.Class;
import org.ahmedukamel.eduai.repository.ClassRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class ClassUpdater implements BiFunction<Class, UpdateClassRequest, Class> {
    private final ClassRepository classRepository;

    @Override
    public Class apply(Class theClass, UpdateClassRequest request) {
        if (!theClass.getName().equalsIgnoreCase(request.name().strip())) {
            DatabaseService.unique(classRepository::existsBySchool_IdAndNameIgnoreCase,
                    theClass.getSchool().getId(), request.name().strip(), Class.class);
        }

        if (!theClass.getNumber().equalsIgnoreCase(request.number().strip())) {
            DatabaseService.unique(classRepository::existsBySchool_IdAndNumberIgnoreCase,
                    theClass.getSchool().getId(), request.number().strip(), Class.class);
        }

        if (!theClass.getGroup().equalsIgnoreCase(request.group().strip())) {
            DatabaseService.unique(classRepository::existsBySchool_IdAndGroupIgnoreCase,
                    theClass.getSchool().getId(), request.group().strip(), Class.class);
        }

        theClass.setName(request.name().strip());
        theClass.setNumber(request.number().strip());
        theClass.setGroup(request.group().strip());
        theClass.setFloor(request.floor());

        return classRepository.save(theClass);
    }
}
package org.ahmedukamel.eduai.updater.classes;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.classes.UpdateClassRequest;
import org.ahmedukamel.eduai.model.Class;
import org.ahmedukamel.eduai.repository.ClassRepository;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class ClassUpdater implements BiFunction<Class, UpdateClassRequest, Class> {
    private final ClassRepository classRepository;

    @Override
    public Class apply(Class theClass, UpdateClassRequest request) {
        theClass.setName(request.name().strip());
        theClass.setNumber(request.number().strip());
        theClass.setGroup(request.group().strip());
        theClass.setFloor(request.floor());

        return classRepository.save(theClass);
    }
}
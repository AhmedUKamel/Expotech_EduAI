package org.ahmedukamel.eduai.mapper.classes;

import org.ahmedukamel.eduai.dto.classes.ClassResponse;
import org.ahmedukamel.eduai.model.Class;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ClassResponseMapper implements Function<Class, ClassResponse> {
    @Override
    public ClassResponse apply(Class theClass) {
        return new ClassResponse(
                theClass.getId(),
                theClass.getName(),
                theClass.getNumber(),
                theClass.getGroup(),
                theClass.getFloor(),
                theClass.getCreatedAt(),
                theClass.getUpdatedAt(),
                theClass.getSchool().getId()
        );
    }
}
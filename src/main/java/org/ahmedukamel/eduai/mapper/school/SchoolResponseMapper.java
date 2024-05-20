package org.ahmedukamel.eduai.mapper.school;

import org.ahmedukamel.eduai.dto.school.SchoolResponse;
import org.ahmedukamel.eduai.model.School;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SchoolResponseMapper implements Function<School, SchoolResponse> {
    @Override
    public SchoolResponse apply(School school) {
        return new SchoolResponse(
                school.getId(),
                school.getName(),
                school.getEstablished(),
                school.getAbout(),
                school.getLanguage(),
                school.getCode(),
                school.getTheme(),
                school.getCreatedAt(),
                school.getUpdatedAt(),
                school.getClasses().size()
        );
    }
}
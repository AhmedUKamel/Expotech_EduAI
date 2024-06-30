package org.ahmedukamel.eduai.mapper.section;

import org.ahmedukamel.eduai.dto.section.SectionResponse;
import org.ahmedukamel.eduai.model.Section;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.function.Function;

@Component
public class SectionResponseMapper implements Function<Section, SectionResponse> {
    @Override
    public SectionResponse apply(Section section) {
        // TODO: Fix this
//        Collection<Long> studentsId = section.getStudents()
//                .stream()
//                .map(Student::getId)
//                .toList();
        Collection<Long> studentsId = null;

        return new SectionResponse(
                section.getId(),
                section.getName(),
                section.getNumber(),
                section.getCreatedAt(),
                section.getUpdatedAt(),
                section.getClassroom().getId(),
                studentsId
        );
    }
}
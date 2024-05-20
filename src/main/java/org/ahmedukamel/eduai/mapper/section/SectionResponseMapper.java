package org.ahmedukamel.eduai.mapper.section;

import org.ahmedukamel.eduai.dto.section.SectionResponse;
import org.ahmedukamel.eduai.model.Section;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SectionResponseMapper implements Function<Section, SectionResponse> {
    @Override
    public SectionResponse apply(Section section) {
        return new SectionResponse(
                section.getId(),
                section.getName(),
                section.getNumber(),
                section.getRoomNumber(),
                section.getCreatedAt(),
                section.getUpdatedAt(),
                section.getTheClass().getId(),
                section.getUser().getId()
        );
    }
}
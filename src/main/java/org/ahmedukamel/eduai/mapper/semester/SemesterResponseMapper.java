package org.ahmedukamel.eduai.mapper.semester;

import org.ahmedukamel.eduai.dto.semester.SemesterResponse;
import org.ahmedukamel.eduai.model.Semester;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SemesterResponseMapper implements Function<Semester, SemesterResponse> {
    @Override
    public SemesterResponse apply(Semester semester) {
        return new SemesterResponse(
                semester.getId(),
                semester.getName(),
                semester.getStartDate(),
                semester.getEndDate()
        );
    }
}
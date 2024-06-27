package org.ahmedukamel.eduai.saver.semester;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.semester.CreateSemesterRequest;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.model.Semester;
import org.ahmedukamel.eduai.repository.SemesterRepository;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class SemesterSaver implements BiFunction<CreateSemesterRequest, School, Semester> {
    private final SemesterRepository semesterRepository;

    @Override
    public Semester apply(CreateSemesterRequest request, School school) {
        if (request.startDate().isAfter(request.endDate())) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }

        Semester semester = Semester
                .builder()
                .name(request.name())
                .startDate(request.startDate())
                .endDate(request.endDate())
                .school(school)
                .build();

        return semesterRepository.save(semester);
    }
}
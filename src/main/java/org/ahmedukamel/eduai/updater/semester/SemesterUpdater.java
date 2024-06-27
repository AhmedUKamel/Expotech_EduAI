package org.ahmedukamel.eduai.updater.semester;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.semester.UpdateSemesterRequest;
import org.ahmedukamel.eduai.model.Semester;
import org.ahmedukamel.eduai.repository.SemesterRepository;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class SemesterUpdater implements BiFunction<Semester, UpdateSemesterRequest, Semester> {
    private final SemesterRepository semesterRepository;

    @Override
    public Semester apply(Semester semester, UpdateSemesterRequest request) {
        if (request.startDate().isAfter(request.endDate())) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }

        semester.setName(request.name());
        semester.setStartDate(request.startDate());
        semester.setEndDate(request.endDate());

        return semesterRepository.save(semester);
    }
}
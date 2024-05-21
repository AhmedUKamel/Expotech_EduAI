package org.ahmedukamel.eduai.updater.semester;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.semester.SemesterRequest;
import org.ahmedukamel.eduai.model.Semester;
import org.ahmedukamel.eduai.repository.SemesterRepository;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class SemesterUpdater implements BiFunction<Semester, SemesterRequest, Semester> {
    private final SemesterRepository semesterRepository;

    @Override
    public Semester apply(Semester semester, SemesterRequest request) {
        semester.setName(request.name());
        semester.setStart(request.start());
        semester.setEnd(request.end());

        return semesterRepository.save(semester);
    }
}
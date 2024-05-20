package org.ahmedukamel.eduai.saver.semester;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.semester.SemesterRequest;
import org.ahmedukamel.eduai.model.Semester;
import org.ahmedukamel.eduai.repository.SemesterRepository;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class SemesterSaver implements Function<SemesterRequest, Semester> {
    private final SemesterRepository semesterRepository;

    @Override
    public Semester apply(SemesterRequest request) {
        Semester semester = new Semester();

        semester.setName(request.name().strip());
        semester.setStart(request.start());
        semester.setEnd(request.end());

        return semesterRepository.save(semester);
    }
}
package org.ahmedukamel.eduai.saver.exam;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.exam.CreateExamRequest;
import org.ahmedukamel.eduai.model.Exam;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.model.Semester;
import org.ahmedukamel.eduai.model.User;
import org.ahmedukamel.eduai.repository.ExamRepository;
import org.ahmedukamel.eduai.repository.SchoolRepository;
import org.ahmedukamel.eduai.repository.SemesterRepository;
import org.ahmedukamel.eduai.repository.UserRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.stereotype.Component;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ExamSaver implements Function<CreateExamRequest, Exam> {
    private final SemesterRepository semesterRepository;
    private final SchoolRepository schoolRepository;
    private final UserRepository userRepository;
    private final ExamRepository repository;
    @Override
    public Exam apply(CreateExamRequest request) {
        Exam exam = Exam
                .builder()
                .name(request.name().strip())
                .term(request.term().strip())
                .startAt(request.startAt())
                .endAt(request.endAt())
                .active(true)
                .user(DatabaseService.get(userRepository::findById, request.userId(), User.class))
                .school(DatabaseService.get(schoolRepository::findById, request.schoolId(), School.class))
                .semester(DatabaseService.get(semesterRepository::findById, request.semesterId(), Semester.class))
                .build();
        return repository.save(exam);
    }
}
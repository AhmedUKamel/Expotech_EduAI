package org.ahmedukamel.eduai.updater.exam;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.exam.UpdateExamRequest;
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

import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class ExamUpdater implements BiFunction<Exam, UpdateExamRequest, Exam> {
    private final SemesterRepository semesterRepository;
    private final SchoolRepository schoolRepository;
    private final UserRepository userRepository;
    private final ExamRepository examRepository;

    @Override
    public Exam apply(Exam exam, UpdateExamRequest request) {
        exam.setName(request.name().strip());
        exam.setTerm(request.term().strip());
        exam.setStartAt(request.startAt());
        exam.setEndAt(request.endAt());

        exam.setActive(request.active());
        exam.setNoticePublished(request.noticePublished());
        exam.setResultPublished(request.resultPublished());

        exam.setSchool(DatabaseService.get(schoolRepository::findById, request.schoolId(), School.class));
        exam.setUser(DatabaseService.get(userRepository::findById, request.userId(), User.class));
        exam.setSemester(DatabaseService.get(semesterRepository::findById, request.semesterId(), Semester.class));

        return examRepository.save(exam);
    }
}
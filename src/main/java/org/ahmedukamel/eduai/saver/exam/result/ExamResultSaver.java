package org.ahmedukamel.eduai.saver.exam.result;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.exam.result.RecordExamResultRequest;
import org.ahmedukamel.eduai.model.Exam;
import org.ahmedukamel.eduai.model.ExamResult;
import org.ahmedukamel.eduai.model.Student;
import org.ahmedukamel.eduai.model.enumeration.ExamResultStatus;
import org.ahmedukamel.eduai.repository.ExamRepository;
import org.ahmedukamel.eduai.repository.ExamResultRepository;
import org.ahmedukamel.eduai.repository.StudentRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ExamResultSaver implements Function<RecordExamResultRequest, ExamResult> {
    private final ExamResultRepository examResultRepository;
    private final StudentRepository studentRepository;
    private final ExamRepository examRepository;

    @Override
    public ExamResult apply(RecordExamResultRequest request) {
        Student student = DatabaseService.get(studentRepository::findById, request.studentId(), Student.class);
        Exam exam = DatabaseService.get(examRepository::findById, request.examId(), Exam.class);

        DatabaseService.unique(examResultRepository::existsByExam_IdAndStudent_Id,
                request.examId(), request.studentId(), ExamResult.class);

        ExamResultStatus status = request.score() >= 50 ? ExamResultStatus.PASSED : ExamResultStatus.FAILED;

        ExamResult examResult = ExamResult
                .builder()
                .exam(exam)
                .student(student)
                .score(request.score())
                .status(status)
                .build();

        return examResultRepository.save(examResult);
    }
}
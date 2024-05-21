package org.ahmedukamel.eduai.service.exam;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.exam.CreateExamRequest;
import org.ahmedukamel.eduai.dto.exam.ExamResponse;
import org.ahmedukamel.eduai.dto.exam.UpdateExamRequest;
import org.ahmedukamel.eduai.mapper.exam.ExamResponseMapper;
import org.ahmedukamel.eduai.model.Exam;
import org.ahmedukamel.eduai.repository.ExamRepository;
import org.ahmedukamel.eduai.saver.exam.ExamSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.updater.exam.ExamUpdater;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamManagementService implements IExamManagementService {
    private final ExamResponseMapper examResponseMapper;
    private final ExamRepository examRepository;
    private final ExamUpdater examUpdater;
    private final ExamSaver examSaver;

    @Override
    public Object createExam(Object object) {
        CreateExamRequest request = (CreateExamRequest) object;

        Exam savedExam = examSaver.apply(request);

        ExamResponse response = examResponseMapper.apply(savedExam);
        String message = "Exam created successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object updateExam(Long id, Object object) {
        Exam exam = DatabaseService.get(examRepository::findById, id, Exam.class);
        UpdateExamRequest request = (UpdateExamRequest) object;

        Exam updatedExam = examUpdater.apply(exam, request);

        ExamResponse response = examResponseMapper.apply(updatedExam);
        String message = "Exam updated successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object deleteExam(Long id) {
        Exam exam = DatabaseService.get(examRepository::findById, id, Exam.class);

        examRepository.delete(exam);

        String message = "Exam deleted successfully.";

        return new ApiResponse(true, message, "");
    }

    @Override
    public Object getExam(Long id) {
        Exam exam = DatabaseService.get(examRepository::findById, id, Exam.class);

        ExamResponse response = examResponseMapper.apply(exam);
        String message = "Exam retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getAllExams(long pageSize, long pageNumber) {
        List<Exam> exams = examRepository
                .selectExamsWithPagination(pageSize, pageSize * (pageNumber - 1));

        List<ExamResponse> response = exams
                .stream()
                .map(examResponseMapper)
                .toList();
        String message = "Exams retrieved successfully.";

        return new ApiResponse(true, message, response);
    }
}
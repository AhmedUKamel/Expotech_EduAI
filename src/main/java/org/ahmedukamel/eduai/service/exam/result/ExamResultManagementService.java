package org.ahmedukamel.eduai.service.exam.result;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.exam.result.ExamResultResponse;
import org.ahmedukamel.eduai.dto.exam.result.RecordExamResultRequest;
import org.ahmedukamel.eduai.mapper.exam.result.ExamResultResponseMapper;
import org.ahmedukamel.eduai.model.ExamResult;
import org.ahmedukamel.eduai.repository.ExamResultRepository;
import org.ahmedukamel.eduai.saver.exam.result.ExamResultSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamResultManagementService implements IExamResultManagementService {
    private final ExamResultResponseMapper examResultResponseMapper;
    private final ExamResultRepository examResultRepository;
    private final ExamResultSaver examResultSaver;

    @Override
    public Object recordExamResult(Object object) {
        RecordExamResultRequest request = (RecordExamResultRequest) object;

        ExamResult savedExamResult = examResultSaver.apply(request);

        ExamResultResponse response = examResultResponseMapper.apply(savedExamResult);
        String message = "Exam result recorded successfully";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object deleteExamResult(Long examResultId) {
        ExamResult examResult = DatabaseService.get(examResultRepository::findById, examResultId, ExamResult.class);

        examResultRepository.delete(examResult);

        String message = "Exam result deleted successfully";

        return new ApiResponse(true, message, "");
    }

    @Override
    public Object getExamResult(Long examResultId) {
        ExamResult examResult = DatabaseService.get(examResultRepository::findById, examResultId, ExamResult.class);

        ExamResultResponse response = examResultResponseMapper.apply(examResult);
        String message = "Exam result retrieved successfully";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getExamResults(int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<ExamResult> examResults = examResultRepository.findAll(pageable);

        Page<ExamResultResponse> response = examResults.map(examResultResponseMapper);
        String message = "Exam results retrieved successfully";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getExamResultsByExam(Long examId, int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<ExamResult> examResults = examResultRepository.findAllByExam_Id(examId, pageable);

        Page<ExamResultResponse> response = examResults.map(examResultResponseMapper);
        String message = "Exam results retrieved successfully";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getExamResultsByStudent(Long studentId, int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<ExamResult> examResults = examResultRepository.findAllByStudent_Id(studentId, pageable);

        Page<ExamResultResponse> response = examResults.map(examResultResponseMapper);
        String message = "Exam results retrieved successfully";

        return new ApiResponse(true, message, response);
    }
}
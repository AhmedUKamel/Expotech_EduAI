package org.ahmedukamel.eduai.service.exam.result;

public interface IExamResultManagementService {
    Object recordExamResult(Object object);

    Object deleteExamResult(Long examResultId);

    Object getExamResult(Long examResultId);

    Object getExamResults(int pageSize, int pageNumber);

    Object getExamResultsByExam(Long examId, int pageSize, int pageNumber);

    Object getExamResultsByStudent(Long studentId, int pageSize, int pageNumber);
}
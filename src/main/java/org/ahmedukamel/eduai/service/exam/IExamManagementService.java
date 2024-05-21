package org.ahmedukamel.eduai.service.exam;

public interface IExamManagementService {
    Object createExam(Object object);

    Object updateExam(Long id, Object object);

    Object deleteExam(Long id);

    Object getExam(Long id);

    Object getAllExams(long pageSize, long pageNumber);
}
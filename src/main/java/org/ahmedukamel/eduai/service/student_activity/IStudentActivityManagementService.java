package org.ahmedukamel.eduai.service.student_activity;

public interface IStudentActivityManagementService {
    Object createStudentActivity(Object object);

    Object updateStudentActivity(Long id, Object object);

    Object deleteStudentActivity(Long id);

    Object getStudentActivity(Long id);

    Object getAllStudentActivities(int pageSize, int pageNumber);
}
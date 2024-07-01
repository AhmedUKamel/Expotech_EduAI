package org.ahmedukamel.eduai.service.teacher;

public interface ITeacherManagementService {
    Object addTeacher(Object object);

    Object deleteTeacher(Long id);

    Object getTeacher(Long id);

    Object getAllTeachers(int pageSize, int pageNumber);
}
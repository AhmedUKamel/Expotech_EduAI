package org.ahmedukamel.eduai.service.student;

public interface IStudentManagementService {

    Object addStudent(Object object);

    Object deleteStudent(Long id);

    Object getStudent(Long id);

    Object getAllStudents(int pageSize, int pageNumber);
}
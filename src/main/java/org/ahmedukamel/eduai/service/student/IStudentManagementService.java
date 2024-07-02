package org.ahmedukamel.eduai.service.student;

public interface IStudentManagementService {
    Object addStudent(Object object);

    Object setStudentAccountLock(Long id, boolean accountLocked);

    Object getStudent(Long id);

    Object getAllStudents(int pageSize, int pageNumber);
}
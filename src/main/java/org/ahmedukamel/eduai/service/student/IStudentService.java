package org.ahmedukamel.eduai.service.student;

public interface IStudentService{
    Object createStudent(Object object);

    Object updateStudent(Long id, Object object);

    Object deleteStudent(Long id);

    Object getStudent(Long id);

    Object getAllStudent(long pageSize, long pageNumber);
}

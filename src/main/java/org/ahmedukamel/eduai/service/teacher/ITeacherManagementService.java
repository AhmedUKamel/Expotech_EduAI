package org.ahmedukamel.eduai.service.teacher;

public interface ITeacherManagementService {
    Object addTeacher(Object object);

    Object setTeacherAccountLock(Long id, boolean accountLocked);

    Object getTeacher(Long id);

    Object getAllTeachers(int pageSize, int pageNumber);
}
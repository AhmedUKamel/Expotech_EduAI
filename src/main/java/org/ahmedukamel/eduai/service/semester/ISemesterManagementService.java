package org.ahmedukamel.eduai.service.semester;

public interface ISemesterManagementService {
    Object createSemester(Object object);

    Object updateSemester(Integer id, Object object);

    Object deleteSemester(Integer id);

    Object getSemester(Integer id);

    Object getAllSemesters(int pageSize, int pageNumber);
}
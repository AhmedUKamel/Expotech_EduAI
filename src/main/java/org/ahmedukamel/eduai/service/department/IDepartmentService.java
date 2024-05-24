package org.ahmedukamel.eduai.service.department;

public interface IDepartmentService {
    Object getDepartment(Integer id);

    Object getAllDepartments(long pageSize, long pageNumber);

    Object getSchoolDepartments(Integer schoolId, long pageSize, long pageNumber);
}
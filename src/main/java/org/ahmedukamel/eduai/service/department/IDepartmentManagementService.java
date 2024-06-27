package org.ahmedukamel.eduai.service.department;

public interface IDepartmentManagementService {
    Object createDepartment(Object object);

    Object updateDepartment(Integer id, Object object);

    Object deleteDepartment(Integer id);

    Object getDepartment(Integer id);

    Object getAllDepartments(long pageSize, long pageNumber);

    Object getSchoolDepartments(Integer schoolId, long pageSize, long pageNumber);
}
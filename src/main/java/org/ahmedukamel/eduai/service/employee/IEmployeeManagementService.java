package org.ahmedukamel.eduai.service.employee;

public interface IEmployeeManagementService {
    Object addEmployee(Object object);

    Object deleteEmployee(Long id);

    Object getEmployee(Long id);

    Object getAllEmployees(int pageSize, int pageNumber);
}
package org.ahmedukamel.eduai.service.employee;

public interface ITempEmployeeService {
    Object assignPositionToEmployee(Object object);

    Object getUnEmployedEmployees(int pageSize, int pageNumber);

    Object getAllEmployees(int pageSize, int pageNumber);

    Object addEmployee(Object object);
}
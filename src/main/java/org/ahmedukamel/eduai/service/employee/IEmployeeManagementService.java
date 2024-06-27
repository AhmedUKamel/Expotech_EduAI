package org.ahmedukamel.eduai.service.employee;

public interface IEmployeeManagementService {
    Object assignPositionToEmployee(Object object);

    Object getUnEmployedEmployees(long pageSize, long pageNumber);
}
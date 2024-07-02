package org.ahmedukamel.eduai.service.employee;

public interface IEmployeeManagementService {
    Object addEmployee(Object object);

    Object setEmployeeAccountLock(Long id, boolean accountLocked);

    Object getEmployee(Long id);

    Object getAllEmployees(int pageSize, int pageNumber);
}
package org.ahmedukamel.eduai.service.employee;

import org.ahmedukamel.eduai.model.enumeration.EmployeeType;

public interface IEmployeeManagementService {
    Object addEmployee(Object object, EmployeeType employeeType);

    Object setEmployeeAccountLock(Long id, boolean accountLocked);

    Object getEmployee(Long id);

    Object getAllEmployees(int pageSize, int pageNumber, EmployeeType employeeType, boolean archived);
}
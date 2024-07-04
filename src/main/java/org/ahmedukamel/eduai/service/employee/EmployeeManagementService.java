package org.ahmedukamel.eduai.service.employee;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.employee.AddEmployeeRequest;
import org.ahmedukamel.eduai.dto.profile.EmployeeProfileResponse;
import org.ahmedukamel.eduai.mapper.profile.EmployeeProfileResponseMapper;
import org.ahmedukamel.eduai.model.Employee;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.model.enumeration.EmployeeType;
import org.ahmedukamel.eduai.repository.EmployeeRepository;
import org.ahmedukamel.eduai.saver.employee.AddEmployeeRequestSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.util.context.ContextHolderUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeManagementService implements IEmployeeManagementService {
    private final EmployeeProfileResponseMapper employeeProfileResponseMapper;
    private final AddEmployeeRequestSaver addEmployeeRequestSaver;
    private final EmployeeRepository employeeRepository;

    @Override
    public Object addEmployee(Object object, EmployeeType employeeType) {
        AddEmployeeRequest request = (AddEmployeeRequest) object;
        School school = ContextHolderUtils.getEmployee().getSchool();

        Employee employee = addEmployeeRequestSaver.apply(request, school, employeeType);

        EmployeeProfileResponse response = employeeProfileResponseMapper.apply(employee);
        String message = "Employee added successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object setEmployeeAccountLock(Long id, boolean accountLocked) {
        School school = ContextHolderUtils.getEmployee().getSchool();
        Employee employee = DatabaseService.get(employeeRepository::findByIdAndSchool_Id,
                id, school.getId(), Employee.class);

        employee.setAccountNonLocked(!accountLocked);
        employeeRepository.save(employee);

        String message = "Employee account lock set successfully.";

        return new ApiResponse(true, message, "");
    }

    @Override
    public Object getEmployee(Long id) {
        School school = ContextHolderUtils.getEmployee().getSchool();

        Employee employee = DatabaseService.get(employeeRepository::findByIdAndSchool_Id,
                id, school.getId(), Employee.class);

        EmployeeProfileResponse response = employeeProfileResponseMapper.apply(employee);
        String message = "Employee retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getAllEmployees(int pageSize, int pageNumber, EmployeeType employeeType, boolean archived) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        School school = ContextHolderUtils.getEmployee().getSchool();

        Page<Employee> employees = employeeRepository.findAllBySchool_IdAndEmployeeTypeAndAccountNonLocked(
                school.getId(), employeeType, !archived, pageable);

        Page<EmployeeProfileResponse> response = employees.map(employeeProfileResponseMapper);
        String message = "All employees retrieved successfully.";

        return new ApiResponse(true, message, response);
    }
}
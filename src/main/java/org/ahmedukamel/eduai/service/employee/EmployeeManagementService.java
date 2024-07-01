package org.ahmedukamel.eduai.service.employee;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.employee.AddEmployeeRequest;
import org.ahmedukamel.eduai.dto.profile.EmployeeProfileResponse;
import org.ahmedukamel.eduai.mapper.profile.EmployeeProfileResponseMapper;
import org.ahmedukamel.eduai.model.Employee;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.model.Teacher;
import org.ahmedukamel.eduai.repository.EmployeeRepository;
import org.ahmedukamel.eduai.saver.employee.AddEmployeeRequestSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.util.context.ContextHolderUtils;
import org.springframework.dao.DataIntegrityViolationException;
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
    public Object addEmployee(Object object) {
        AddEmployeeRequest request = (AddEmployeeRequest) object;
        School school = ContextHolderUtils.getEmployee().getSchool();

        Employee employee = addEmployeeRequestSaver.apply(request, school);

        EmployeeProfileResponse response = employeeProfileResponseMapper.apply(employee);
        String message = "Employee added successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object deleteEmployee(Long id) {
        School school = ContextHolderUtils.getEmployee().getSchool();
        Employee employee = DatabaseService.get(employeeRepository::findByIdAndSchool_Id,
                id, school.getId(), Teacher.class);

        try {
            employeeRepository.delete(employee);
        } catch (DataIntegrityViolationException exception) {
            throw new RuntimeException("Employee is associated with other records and can't be deleted.", exception);
        }

        String message = "Employee deleted successfully.";

        return new ApiResponse(true, message, "");
    }

    @Override
    public Object getEmployee(Long id) {
        School school = ContextHolderUtils.getEmployee().getSchool();

        Employee employee = DatabaseService.get(employeeRepository::findByIdAndSchool_Id,
                id, school.getId(), Teacher.class);

        EmployeeProfileResponse response = employeeProfileResponseMapper.apply(employee);
        String message = "Employee retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getAllEmployees(int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        School school = ContextHolderUtils.getEmployee().getSchool();

        Page<Employee> employees = employeeRepository.findAllBySchool_Id(school.getId(), pageable);

        Page<EmployeeProfileResponse> response = employees.map(employeeProfileResponseMapper);
        String message = "All employees retrieved successfully.";

        return new ApiResponse(true, message, response);
    }
}
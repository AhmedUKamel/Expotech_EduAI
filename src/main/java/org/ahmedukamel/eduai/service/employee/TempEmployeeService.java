package org.ahmedukamel.eduai.service.employee;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.employee.AddEmployeeRequest;
import org.ahmedukamel.eduai.dto.employee.AssignPositionToEmployeeRequest;
import org.ahmedukamel.eduai.dto.profile.EmployeeProfileResponse;
import org.ahmedukamel.eduai.mapper.profile.EmployeeProfileResponseMapper;
import org.ahmedukamel.eduai.model.Employee;
import org.ahmedukamel.eduai.model.Position;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.model.enumeration.EmployeeType;
import org.ahmedukamel.eduai.repository.EmployeeRepository;
import org.ahmedukamel.eduai.repository.PositionRepository;
import org.ahmedukamel.eduai.saver.employee.AddEmployeeRequestSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.util.context.ContextHolderUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TempEmployeeService implements ITempEmployeeService {
    private final EmployeeProfileResponseMapper employeeProfileResponseMapper;
    private final AddEmployeeRequestSaver addEmployeeRequestSaver;
    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;

    @Override
    public Object assignPositionToEmployee(Object object) {
        AssignPositionToEmployeeRequest request = (AssignPositionToEmployeeRequest) object;
        School school = ContextHolderUtils.getEmployee().getSchool();

        Employee employee = DatabaseService.get(employeeRepository::findByIdAndSchool_Id, request.employeeId(), school.getId(), Employee.class);

        Position position = DatabaseService.get(positionRepository::findByIdAndDepartment_School_Id, request.positionId(), school.getId(), Position.class);

        employee.setPosition(position);
        employee.setSalary(request.salary());
        employee.setHireDate(request.hireDate());

        Employee updatedEmployee = employeeRepository.save(employee);

        EmployeeProfileResponse response = employeeProfileResponseMapper.apply(updatedEmployee);
        String message = "Position successfully assigned to employee.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getUnEmployedEmployees(int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        School school = ContextHolderUtils.getEmployee().getSchool();

        Page<Employee> employees = employeeRepository.findAllByPositionIsNullAndSchool_Id(school.getId(), pageable);

        Page<EmployeeProfileResponse> response = employees.map(employeeProfileResponseMapper);
        String message = "Unemployed employees retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getAllEmployees(int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        School school = ContextHolderUtils.getEmployee().getSchool();

        Page<Employee> employees = employeeRepository.findAllBySchool_Id(school.getId(), pageable);

        Page<EmployeeProfileResponse> response = employees.map(employeeProfileResponseMapper);
        String message = "All employees retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object addEmployee(Object object) {
        AddEmployeeRequest request = (AddEmployeeRequest) object;
        School school = ContextHolderUtils.getEmployee().getSchool();

        Employee savedEmployee = addEmployeeRequestSaver.apply(request, school, EmployeeType.EMPLOYEE);

        EmployeeProfileResponse response = employeeProfileResponseMapper.apply(savedEmployee);
        String message = "Employee added successfully";

        return new ApiResponse(true, message, response);
    }
}
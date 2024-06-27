package org.ahmedukamel.eduai.service.employee;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.employee.AssignPositionToEmployeeRequest;
import org.ahmedukamel.eduai.dto.exam.ExamResponse;
import org.ahmedukamel.eduai.dto.profile.EmployeeProfileResponse;
import org.ahmedukamel.eduai.mapper.profile.EmployeeProfileResponseMapper;
import org.ahmedukamel.eduai.model.Employee;
import org.ahmedukamel.eduai.model.Position;
import org.ahmedukamel.eduai.repository.EmployeeRepository;
import org.ahmedukamel.eduai.repository.PositionRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeManagementService implements IEmployeeManagementService {
    private final EmployeeProfileResponseMapper employeeProfileResponseMapper;
    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;

    @Override
    public Object assignPositionToEmployee(Object object) {
        AssignPositionToEmployeeRequest request = (AssignPositionToEmployeeRequest) object;

        Employee employee = DatabaseService.get(employeeRepository::findById, request.employeeId(), Employee.class);
        Position position = DatabaseService.get(positionRepository::findById, request.positionId(), Position.class);

        employee.setPosition(position);
        employee.setSalary(request.salary());
        employee.setHireDate(request.hireDate());

        Employee updatedEmployee = employeeRepository.save(employee);
        EmployeeProfileResponse response = employeeProfileResponseMapper.apply(updatedEmployee);
        String message = "Position successfully assigned to employee.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getUnEmployedEmployees(long pageSize, long pageNumber) {
        List<Employee> employees = employeeRepository
                .selectUnEmployedEmployeesWithPagination(pageSize, pageSize * (pageNumber - 1));

        List<EmployeeProfileResponse> response = employees
                .stream()
                .map(employeeProfileResponseMapper)
                .toList();
        String message = "Employees retrieved successfully.";

        return new ApiResponse(true, message, response);
    }
}
package org.ahmedukamel.eduai.saver.employee;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.employee.AddEmployeeRequest;
import org.ahmedukamel.eduai.mapper.phonenumber.PhoneNumberMapper;
import org.ahmedukamel.eduai.mapper.user.UserRegistrationRequestMapper;
import org.ahmedukamel.eduai.model.Employee;
import org.ahmedukamel.eduai.model.Position;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.model.embeddable.PhoneNumber;
import org.ahmedukamel.eduai.model.enumeration.EmployeeStatus;
import org.ahmedukamel.eduai.model.enumeration.EmployeeType;
import org.ahmedukamel.eduai.model.enumeration.Role;
import org.ahmedukamel.eduai.repository.EmployeeRepository;
import org.ahmedukamel.eduai.repository.PositionRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddEmployeeRequestSaver {

    private final UserRegistrationRequestMapper<Employee> userRegistrationRequestMapper;
    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final PhoneNumberMapper phoneNumberMapper;

    public Employee apply(AddEmployeeRequest request, School school, EmployeeType employeeType) {
        PhoneNumber phoneNumber = phoneNumberMapper.apply(request.number());
        Position position = DatabaseService.get(positionRepository::findByIdAndDepartment_School_Id,
                request.positionId(), school.getId(), Position.class);

        Employee employee = userRegistrationRequestMapper.apply(request, Employee.class);

        employee.setPhoneNumber(phoneNumber);
        employee.setRole(Role.EMPLOYEE);
        employee.setSchool(school);
        employee.setSalary(request.salary());
        employee.setHireDate(request.hireDate());
        employee.setPosition(position);
        employee.setEmployeeType(employeeType);
        employee.setEmployeeStatus(EmployeeStatus.ACTIVE);

        return employeeRepository.save(employee);
    }
}
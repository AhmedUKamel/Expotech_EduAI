package org.ahmedukamel.eduai.saver.employee;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.employee.EmployeeRegistrationRequest;
import org.ahmedukamel.eduai.mapper.phonenumber.PhoneNumberMapper;
import org.ahmedukamel.eduai.mapper.user.UserRegistrationRequestMapper;
import org.ahmedukamel.eduai.model.Employee;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.model.embeddable.PhoneNumber;
import org.ahmedukamel.eduai.model.enumeration.EmployeeRole;
import org.ahmedukamel.eduai.model.enumeration.EmployeeStatus;
import org.ahmedukamel.eduai.model.enumeration.Role;
import org.ahmedukamel.eduai.repository.EmployeeRepository;
import org.ahmedukamel.eduai.repository.SchoolRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class EmployeeRegistrationRequestSaver
        implements Function<EmployeeRegistrationRequest, Employee> {

    private final UserRegistrationRequestMapper<Employee> userRegistrationRequestMapper;
    private final EmployeeRepository employeeRepository;
    private final PhoneNumberMapper phoneNumberMapper;
    private final SchoolRepository schoolRepository;

    @Override
    public Employee apply(EmployeeRegistrationRequest request) {
        PhoneNumber phoneNumber = phoneNumberMapper.apply(request.number());
        School school = DatabaseService.get(schoolRepository::findById, request.schoolId(), School.class);

        Employee employee = userRegistrationRequestMapper.apply(request, Employee.class);

        employee.setPhoneNumber(phoneNumber);
        employee.setRole(Role.EMPLOYEE);
        employee.setQualification(request.qualification());
        employee.setEmployeeType(request.employeeType());
        employee.setEmployeeStatus(EmployeeStatus.ACTIVE);
        employee.setSchool(school);

        employee.setEmployeeRoles(Set.of(EmployeeRole.ADMIN)); // TODO: Will be deleted

        return employeeRepository.save(employee);
    }
}
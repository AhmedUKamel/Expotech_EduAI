package org.ahmedukamel.eduai.saver.auth;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.auth.EmployeeRegistrationRequest;
import org.ahmedukamel.eduai.mapper.phonenumber.PhoneNumberMapper;
import org.ahmedukamel.eduai.model.Employee;
import org.ahmedukamel.eduai.model.User;
import org.ahmedukamel.eduai.model.embeddable.PhoneNumber;
import org.ahmedukamel.eduai.model.enumeration.Role;
import org.ahmedukamel.eduai.repository.EmployeeRepository;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class EmployeeSaver implements Function<EmployeeRegistrationRequest, Employee> {
    private final EmployeeRepository employeeRepository;
    private final PhoneNumberMapper phoneNumberMapper;
    private final UserSaver userSaver;

    @Override
    public Employee apply(EmployeeRegistrationRequest request) {
        PhoneNumber phoneNumber = phoneNumberMapper.apply(request.number());

        User savedUser = userSaver.apply(request, Role.EMPLOYEE);

        Employee employee = Employee
                .builder()
                .phoneNumber(phoneNumber)
                .user(savedUser)
                .build();

        return employeeRepository.save(employee);
    }
}
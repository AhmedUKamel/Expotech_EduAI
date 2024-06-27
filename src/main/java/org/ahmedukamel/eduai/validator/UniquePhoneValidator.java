package org.ahmedukamel.eduai.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.annotation.UniquePhone;
import org.ahmedukamel.eduai.annotation.enumeration.UniquePhoneConstraint;
import org.ahmedukamel.eduai.mapper.phonenumber.PhoneNumberMapper;
import org.ahmedukamel.eduai.repository.EmployeeRepository;
import org.ahmedukamel.eduai.repository.ParentRepository;
import org.ahmedukamel.eduai.repository.TeacherRepository;

import java.util.Objects;

@RequiredArgsConstructor
public class UniquePhoneValidator implements ConstraintValidator<UniquePhone, String> {
    private final EmployeeRepository employeeRepository;
    private final PhoneNumberMapper phoneNumberMapper;
    private final TeacherRepository teacherRepository;
    private final ParentRepository parentRepository;

    private UniquePhoneConstraint constraint;

    @Override
    public void initialize(UniquePhone constraintAnnotation) {
        constraint = constraintAnnotation.entity();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        try {
            return Objects.isNull(value) || switch (constraint) {
                case TEACHER -> !teacherRepository.existsByPhoneNumber(phoneNumberMapper.apply(value));
                case PARENT -> !parentRepository.existsByPhoneNumber(phoneNumberMapper.apply(value));
                case EMPLOYEE -> !employeeRepository.existsByPhoneNumber(phoneNumberMapper.apply(value));
            };
        } catch (Exception ignored) {
            return false;
        }
    }
}
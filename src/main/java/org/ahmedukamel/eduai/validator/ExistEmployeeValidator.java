package org.ahmedukamel.eduai.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.annotation.ExistEmployee;
import org.ahmedukamel.eduai.repository.EmployeeRepository;

import java.util.Objects;

@RequiredArgsConstructor
public class ExistEmployeeValidator implements ConstraintValidator<ExistEmployee, Long> {
    private final EmployeeRepository repository;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        return Objects.isNull(id) || repository.existsById(id);
    }
}
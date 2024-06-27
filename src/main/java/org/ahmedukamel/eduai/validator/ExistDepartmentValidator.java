package org.ahmedukamel.eduai.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.annotation.ExistDepartment;
import org.ahmedukamel.eduai.repository.DepartmentRepository;

import java.util.Objects;

@RequiredArgsConstructor
public class ExistDepartmentValidator implements ConstraintValidator<ExistDepartment, Integer> {
    private final DepartmentRepository repository;

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {
        return Objects.isNull(id) || repository.existsById(id);
    }
}
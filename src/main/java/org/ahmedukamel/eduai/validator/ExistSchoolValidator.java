package org.ahmedukamel.eduai.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.annotation.ExistSchool;
import org.ahmedukamel.eduai.repository.SchoolRepository;

import java.util.Objects;

@RequiredArgsConstructor
public class ExistSchoolValidator implements ConstraintValidator<ExistSchool, Integer> {
    private final SchoolRepository repository;

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {
        return Objects.isNull(id) || repository.existsById(id);
    }
}
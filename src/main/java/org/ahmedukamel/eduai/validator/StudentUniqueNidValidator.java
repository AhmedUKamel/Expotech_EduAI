package org.ahmedukamel.eduai.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.annotation.StudentUniqueNid;
import org.ahmedukamel.eduai.repository.StudentRepository;

import java.util.Objects;

@RequiredArgsConstructor
public class StudentUniqueNidValidator implements ConstraintValidator<StudentUniqueNid, String> {
    private final StudentRepository repository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return Objects.isNull(value) || !repository.existsByNid(value.strip());
    }
}
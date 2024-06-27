package org.ahmedukamel.eduai.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.annotation.ExistStudent;
import org.ahmedukamel.eduai.repository.StudentRepository;

import java.util.Objects;

@RequiredArgsConstructor
public class ExistStudentValidator implements ConstraintValidator<ExistStudent, Long> {
    private final StudentRepository repository;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        return Objects.isNull(id) || repository.existsById(id);
    }
}
package org.ahmedukamel.eduai.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.annotation.ExistSemester;
import org.ahmedukamel.eduai.repository.SemesterRepository;

import java.util.Objects;

@RequiredArgsConstructor
public class ExistSemesterValidator implements ConstraintValidator<ExistSemester, Integer> {
    private final SemesterRepository repository;

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {
        return Objects.isNull(id) || repository.existsById(id);
    }
}
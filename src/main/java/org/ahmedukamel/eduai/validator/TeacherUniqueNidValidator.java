package org.ahmedukamel.eduai.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.annotation.TeacherUniqueNid;
import org.ahmedukamel.eduai.repository.TeacherRepository;

import java.util.Objects;

@RequiredArgsConstructor
public class TeacherUniqueNidValidator implements ConstraintValidator<TeacherUniqueNid, String> {
    private final TeacherRepository repository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return Objects.isNull(value) || !repository.existsByNid(value.strip());
    }
}
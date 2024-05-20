package org.ahmedukamel.eduai.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.annotation.ExistUser;
import org.ahmedukamel.eduai.repository.UserRepository;

import java.util.Objects;

@RequiredArgsConstructor
public class ExistUserValidator implements ConstraintValidator<ExistUser, Long> {
    private final UserRepository repository;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        return Objects.isNull(id) || repository.existsById(id);
    }
}
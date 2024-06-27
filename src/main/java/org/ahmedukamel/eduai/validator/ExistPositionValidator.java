package org.ahmedukamel.eduai.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.annotation.ExistPosition;
import org.ahmedukamel.eduai.repository.PositionRepository;

import java.util.Objects;

@RequiredArgsConstructor
public class ExistPositionValidator implements ConstraintValidator<ExistPosition, Integer> {
    private final PositionRepository repository;

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {
        return Objects.isNull(id) || repository.existsById(id);
    }
}
package org.ahmedukamel.eduai.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.annotation.ExistRegion;
import org.ahmedukamel.eduai.repository.RegionRepository;

import java.util.Objects;

@RequiredArgsConstructor
public class ExistRegionValidator implements ConstraintValidator<ExistRegion, Integer> {
    private final RegionRepository repository;

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {
        return Objects.isNull(id) || repository.existsById(id);
    }
}
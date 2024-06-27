package org.ahmedukamel.eduai.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.annotation.ExistParent;
import org.ahmedukamel.eduai.repository.ParentRepository;

import java.util.Objects;

@RequiredArgsConstructor
public class ExistParentValidator implements ConstraintValidator<ExistParent, Long> {
    private final ParentRepository repository;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        return Objects.isNull(id) || repository.existsById(id);
    }
}
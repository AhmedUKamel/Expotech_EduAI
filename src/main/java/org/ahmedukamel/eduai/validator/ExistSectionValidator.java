package org.ahmedukamel.eduai.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.annotation.ExistSection;
import org.ahmedukamel.eduai.repository.SectionRepository;

import java.util.Objects;

@RequiredArgsConstructor
public class ExistSectionValidator implements ConstraintValidator<ExistSection, Integer> {
    private final SectionRepository repository;

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext constraintValidatorContext) {
        return Objects.isNull(id) || repository.existsById(id);
    }
}
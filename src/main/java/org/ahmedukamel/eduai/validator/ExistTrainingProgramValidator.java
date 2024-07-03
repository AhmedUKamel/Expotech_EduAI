package org.ahmedukamel.eduai.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.ahmedukamel.eduai.annotation.ExistTrainingProgram;
import org.ahmedukamel.eduai.repository.TrainingProgramRepository;

import java.util.Objects;

public class ExistTrainingProgramValidator implements ConstraintValidator<ExistTrainingProgram,Long> {
    private final TrainingProgramRepository repository;

    public ExistTrainingProgramValidator(TrainingProgramRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        return Objects.isNull(id) || repository.existsById(id);
    }
}

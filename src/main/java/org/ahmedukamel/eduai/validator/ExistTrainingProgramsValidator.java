package org.ahmedukamel.eduai.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.ahmedukamel.eduai.annotation.ExistTrainingProgram;
import org.ahmedukamel.eduai.repository.TrainingProgramRepository;

import java.util.Collection;
import java.util.stream.Stream;

public class ExistTrainingProgramsValidator implements ConstraintValidator<ExistTrainingProgram, Collection<Long>> {
    private final TrainingProgramRepository repository;

    public ExistTrainingProgramsValidator(TrainingProgramRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(Collection<Long> trainingProgramIds, ConstraintValidatorContext constraintValidatorContext) {
        Stream<Long> trainingProgramIdStream = trainingProgramIds.stream().flatMap(Stream::ofNullable);
        return trainingProgramIdStream.allMatch(repository::existsById);
    }
}

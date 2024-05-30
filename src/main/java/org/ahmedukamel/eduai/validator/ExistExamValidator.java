package org.ahmedukamel.eduai.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.annotation.ExistExam;
import org.ahmedukamel.eduai.repository.ExamRepository;

import java.util.Objects;

@RequiredArgsConstructor
public class ExistExamValidator implements ConstraintValidator<ExistExam, Long> {
    private final ExamRepository repository;

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext constraintValidatorContext) {
        return Objects.isNull(id) || repository.existsById(id);
    }
}
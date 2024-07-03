package org.ahmedukamel.eduai.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.ahmedukamel.eduai.annotation.ExistTeacher;
import org.ahmedukamel.eduai.repository.TeacherRepository;

import java.util.Objects;

public class ExistTeacherValidator implements ConstraintValidator<ExistTeacher , Long> {
    private final TeacherRepository repository;

    public ExistTeacherValidator(TeacherRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(Long id, ConstraintValidatorContext context) {
        return Objects.isNull(id) || repository.existsById(id);
    }
}

package org.ahmedukamel.eduai.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.ahmedukamel.eduai.annotation.ExistTeacher;
import org.ahmedukamel.eduai.repository.TeacherRepository;

import java.util.Collection;
import java.util.stream.Stream;

public class ExistTeachersValidator implements ConstraintValidator<ExistTeacher , Collection<Long>> {
    private final TeacherRepository repository;

    public ExistTeachersValidator(TeacherRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(Collection<Long> teachersId, ConstraintValidatorContext context) {
        Stream<Long> teacherIdStream = teachersId.stream().flatMap(Stream::ofNullable);
        return teacherIdStream.allMatch(repository::existsById);
    }
}

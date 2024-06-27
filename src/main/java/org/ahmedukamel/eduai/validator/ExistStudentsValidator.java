package org.ahmedukamel.eduai.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.annotation.ExistStudent;
import org.ahmedukamel.eduai.repository.StudentRepository;

import java.util.Collection;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class ExistStudentsValidator implements ConstraintValidator<ExistStudent, Collection<Long>> {
    private final StudentRepository repository;

    @Override
    public boolean isValid(Collection<Long> studentIds, ConstraintValidatorContext constraintValidatorContext) {
        Stream<Long> studentIdStream = studentIds.stream().flatMap(Stream::ofNullable);
        return studentIdStream.allMatch(repository::existsById);
    }
}
package org.ahmedukamel.eduai.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.ahmedukamel.eduai.validator.ExistTrainingProgramValidator;
import org.ahmedukamel.eduai.validator.ExistTrainingProgramsValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.PARAMETER, ElementType.FIELD})
@Constraint(validatedBy = {ExistTrainingProgramValidator.class , ExistTrainingProgramsValidator.class})
public @interface ExistTrainingProgram {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

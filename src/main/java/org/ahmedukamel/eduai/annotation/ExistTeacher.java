package org.ahmedukamel.eduai.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.ahmedukamel.eduai.validator.ExistTeacherValidator;
import org.ahmedukamel.eduai.validator.ExistTeachersValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.PARAMETER, ElementType.FIELD})
@Constraint(validatedBy = {ExistTeacherValidator.class, ExistTeachersValidator.class})
public @interface ExistTeacher {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

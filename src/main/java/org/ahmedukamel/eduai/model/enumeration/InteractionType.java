package org.ahmedukamel.eduai.model.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InteractionType {
    PARENT_TO_TEACHER(Role.PARENT, Role.TEACHER),
    STUDENT_TO_TEACHER(Role.STUDENT, Role.TEACHER),
    TEACHER_TO_STUDENT(Role.TEACHER, Role.STUDENT);

    private final Role from;
    private final Role to;
}
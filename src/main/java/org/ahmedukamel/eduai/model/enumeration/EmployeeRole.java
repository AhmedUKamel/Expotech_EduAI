package org.ahmedukamel.eduai.model.enumeration;

import org.springframework.security.core.GrantedAuthority;

public enum EmployeeRole implements GrantedAuthority {

    ADMIN,
    EMPLOYEE,
    SEMESTER_MANAGER,
    EMPLOYEE_MANAGER,
    TEACHER_MANAGER,
    STUDENT_MANAGER,
    PARENT_MANAGER,
    BUS_MANAGER,
    EVENT_MANAGER,
    TRAINING_PROGRAM_MANAGER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
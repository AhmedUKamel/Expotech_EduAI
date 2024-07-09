package org.ahmedukamel.eduai.model.enumeration;

import org.springframework.security.core.GrantedAuthority;

public enum EmployeeRole implements GrantedAuthority {
    ADMIN,
    SEMESTER_MANAGER,
    EMPLOYEE_MANAGER,
    TEACHER_MANAGER,
    STUDENT_MANAGER,
    COURSE_MANAGER,
    PARENT_MANAGER,
    BUS_MANAGER,
    EVENT_MANAGER,
    DEPARTMENT_MANAGER,
    POSITION_MANAGER,
    TRAINING_PROGRAM_MANAGER,
    INVOICE_MANAGER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
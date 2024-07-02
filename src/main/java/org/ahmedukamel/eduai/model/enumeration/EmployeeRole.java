package org.ahmedukamel.eduai.model.enumeration;

import org.springframework.security.core.GrantedAuthority;

public enum EmployeeRole implements GrantedAuthority {

    ADMIN,
    SEMESTER_MANAGER,
    EMPLOYEE_MANAGER,
    TEACHER_MANAGER,
    STUDENT_MANAGER,
    PARENT_MANAGER,
<<<<<<< Updated upstream
    BUS_MANAGER,
    EVENT_MANAGER;
=======
    TRAINING_PROGRAM_MANAGER;
>>>>>>> Stashed changes

    @Override
    public String getAuthority() {
        return this.name();
    }
}
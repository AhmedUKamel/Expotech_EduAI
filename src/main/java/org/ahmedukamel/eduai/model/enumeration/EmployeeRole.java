package org.ahmedukamel.eduai.model.enumeration;

import org.springframework.security.core.GrantedAuthority;

public enum EmployeeRole implements GrantedAuthority {
    ADMIN,
    SEMESTER_MANAGER,
    EMPLOYEE_MANAGER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
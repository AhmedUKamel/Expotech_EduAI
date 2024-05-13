package org.ahmedukamel.eduai.model.enumeration;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    SUPER_ADMIN, ADMIN, TEACHER, STUDENT, PARENT, EMPLOYEE;

    @Override
    public String getAuthority() {
        return name();
    }
}
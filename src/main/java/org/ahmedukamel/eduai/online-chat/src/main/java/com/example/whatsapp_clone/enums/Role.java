package com.example.whatsapp_clone.enums;

import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

public enum Role {
    TEACHER, STUDENT, ADMIN, PARENT,EMPLOYEE;

    private static final Set<Role> EMPLOYEE_SET = EnumSet.of(Role.EMPLOYEE, Role.TEACHER, Role.PARENT);
    private static final Set<Role> TEACHER_SET = EnumSet.of(Role.TEACHER, Role.PARENT, Role.STUDENT);
    private static final Set<Role> STUDENT_SET = EnumSet.of(Role.TEACHER, Role.PARENT);
    private static final Set<Role> PARENT_SET = EnumSet.of(Role.TEACHER, Role.STUDENT, Role.EMPLOYEE);
    private static final Set<Role> ADMIN_SET = EnumSet.allOf(Role.class); // Admin can chat with any role

    public static final Map<Role, Set<Role>> VALID_ROLES_MAP = Map.of(
            Role.ADMIN, ADMIN_SET,
            Role.TEACHER, TEACHER_SET,
            Role.EMPLOYEE, EMPLOYEE_SET,
            Role.PARENT, PARENT_SET,
            Role.STUDENT, STUDENT_SET
    );

    public static boolean isValidRoleForUser(Role reqUserRole, Role targetUserRole) {
        Set<Role> validRoles = VALID_ROLES_MAP.get(reqUserRole);
        return validRoles != null && validRoles.contains(targetUserRole);
    }
}

package org.ahmedukamel.eduai.model.enumeration;

import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

public enum ChatRole {
    TEACHER, STUDENT, ADMIN, PARENT,EMPLOYEE;

    private static final Set<ChatRole> EMPLOYEE_SET = EnumSet.of(ChatRole.EMPLOYEE, ChatRole.TEACHER, ChatRole.PARENT);
    private static final Set<ChatRole> TEACHER_SET = EnumSet.of(ChatRole.TEACHER, ChatRole.PARENT, ChatRole.STUDENT);
    private static final Set<ChatRole> STUDENT_SET = EnumSet.of(ChatRole.TEACHER, ChatRole.PARENT);
    private static final Set<ChatRole> PARENT_SET = EnumSet.of(ChatRole.TEACHER, ChatRole.STUDENT, ChatRole.EMPLOYEE);
    private static final Set<ChatRole> ADMIN_SET = EnumSet.allOf(ChatRole.class); // Admin can chat with any role

    public static final Map<ChatRole, Set<ChatRole>> VALID_ROLES_MAP = Map.of(
            ChatRole.ADMIN, ADMIN_SET,
            ChatRole.TEACHER, TEACHER_SET,
            ChatRole.EMPLOYEE, EMPLOYEE_SET,
            ChatRole.PARENT, PARENT_SET,
            ChatRole.STUDENT, STUDENT_SET
    );

    public static boolean isValidRoleForUser(ChatRole reqUserRole, ChatRole targetUserRole) {
        Set<ChatRole> validRoles = VALID_ROLES_MAP.get(reqUserRole);
        return validRoles != null && validRoles.contains(targetUserRole);
    }
}

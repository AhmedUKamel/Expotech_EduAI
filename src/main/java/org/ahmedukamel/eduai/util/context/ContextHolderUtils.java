package org.ahmedukamel.eduai.util.context;

import org.ahmedukamel.eduai.model.*;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public class ContextHolderUtils {
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static Object getPrincipal() {
        Objects.requireNonNull(getAuthentication());
        return getAuthentication().getPrincipal();
    }

    public static User getUser() {
        Objects.requireNonNull(getPrincipal());
        if (getPrincipal() instanceof User user) {
            return user;
        }
        throw new IllegalStateException();
    }

    public static Employee getEmployee() {
        if (getUser() instanceof Employee employee) {
            return employee;
        }
        throw new IllegalArgumentException("User is not employee");
    }
    public static Parent getParent() {
        if (getUser() instanceof Parent parent) {
            return parent;
        }
        throw new IllegalArgumentException("User is not parent");
    }

    public static Teacher getTeacher() {
        if (getUser() instanceof Teacher teacher) {
            return teacher;
        }
        throw new IllegalArgumentException("User is not Teacher");
    }

    public static School getSchool() {
        return getEmployee().getSchool();
    }

    public static String getLanguage() {
        return LocaleContextHolder.getLocale().getLanguage();
    }
}
package org.ahmedukamel.eduai.util.department;

import org.ahmedukamel.eduai.model.Department;
import org.ahmedukamel.eduai.model.DepartmentDetails;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.lang.NonNull;

public class DepartmentUtils {
    public static DepartmentDetails getDepartmentDetails(@NonNull Department department, @NonNull Language language) {
        return department.getDetails()
                .stream()
                .filter(departmentDetails -> departmentDetails.getLanguage().equals(language))
                .findFirst()
                .orElseThrow();
    }

    public static DepartmentDetails getDepartmentDetails(@NonNull Department department) {
        return department.getDetails()
                .stream()
                .filter(departmentDetails -> departmentDetails.getLanguage().getCode()
                        .equalsIgnoreCase(LocaleContextHolder.getLocale().getLanguage()))
                .findFirst()
                .orElseThrow();
    }
}
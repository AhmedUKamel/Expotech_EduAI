package org.ahmedukamel.eduai.mapper.department;

import org.ahmedukamel.eduai.dto.department.DepartmentResponse;
import org.ahmedukamel.eduai.model.Department;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.util.department.DepartmentUtils;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class DepartmentResponseMapper implements Function<Department, DepartmentResponse> {
    @Override
    public DepartmentResponse apply(Department department) {
        return new DepartmentResponse(
                department.getId(),
                department.getSchool().getId(),
                DepartmentUtils.getDepartmentDetails(department, Language.ENGLISH).getName(),
                DepartmentUtils.getDepartmentDetails(department, Language.ARABIC).getName(),
                DepartmentUtils.getDepartmentDetails(department, Language.FRENCH).getName()
        );
    }
}
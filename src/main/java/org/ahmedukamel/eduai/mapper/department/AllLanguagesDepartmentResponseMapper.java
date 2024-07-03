package org.ahmedukamel.eduai.mapper.department;

import org.ahmedukamel.eduai.dto.department.AllLanguagesDepartmentResponse;
import org.ahmedukamel.eduai.model.Department;
import org.ahmedukamel.eduai.model.DepartmentDetails;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.util.department.DepartmentUtils;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AllLanguagesDepartmentResponseMapper
        implements Function<Department, AllLanguagesDepartmentResponse> {

    @Override
    public AllLanguagesDepartmentResponse apply(Department department) {
        DepartmentDetails departmentDetails_en = DepartmentUtils.getDepartmentDetails(department, Language.ENGLISH);
        DepartmentDetails departmentDetails_ar = DepartmentUtils.getDepartmentDetails(department, Language.ARABIC);
        DepartmentDetails departmentDetails_fr = DepartmentUtils.getDepartmentDetails(department, Language.FRENCH);

        return new AllLanguagesDepartmentResponse(
                department.getId(),
                departmentDetails_en.getName(),
                departmentDetails_ar.getName(),
                departmentDetails_fr.getName(),
                departmentDetails_en.getDescription(),
                departmentDetails_ar.getDescription(),
                departmentDetails_fr.getDescription(),
                departmentDetails_en.getAbbreviation(),
                departmentDetails_ar.getAbbreviation(),
                departmentDetails_fr.getAbbreviation()
        );
    }
}
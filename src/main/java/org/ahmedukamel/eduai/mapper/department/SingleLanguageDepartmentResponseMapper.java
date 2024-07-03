package org.ahmedukamel.eduai.mapper.department;

import org.ahmedukamel.eduai.dto.department.SingleLanguageDepartmentResponse;
import org.ahmedukamel.eduai.model.Department;
import org.ahmedukamel.eduai.model.DepartmentDetails;
import org.ahmedukamel.eduai.util.department.DepartmentUtils;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SingleLanguageDepartmentResponseMapper
        implements Function<Department, SingleLanguageDepartmentResponse> {

    @Override
    public SingleLanguageDepartmentResponse apply(Department department) {
        DepartmentDetails departmentDetails = DepartmentUtils.getDepartmentDetails(department);

        return new SingleLanguageDepartmentResponse(
                department.getId(),
                departmentDetails.getName(),
                departmentDetails.getDescription(),
                departmentDetails.getAbbreviation()
        );
    }
}
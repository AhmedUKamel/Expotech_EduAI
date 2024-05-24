package org.ahmedukamel.eduai.mapper.department;

import org.ahmedukamel.eduai.dto.department.LocalizedDepartmentResponse;
import org.ahmedukamel.eduai.model.Department;
import org.ahmedukamel.eduai.util.department.DepartmentUtils;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class LocalizedDepartmentResponseMapper implements Function<Department, LocalizedDepartmentResponse> {
    @Override
    public LocalizedDepartmentResponse apply(Department department) {
        return new LocalizedDepartmentResponse(
                department.getId(),
                department.getSchool().getId(),
                DepartmentUtils.getDepartmentDetails(department).getName()
        );
    }
}
package org.ahmedukamel.eduai.mapper.department;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.department.SingleLanguageDepartmentResponse;
import org.ahmedukamel.eduai.model.Department;
import org.ahmedukamel.eduai.model.DepartmentDetails;
import org.ahmedukamel.eduai.service.message.MessageSourceService;
import org.ahmedukamel.eduai.util.department.DepartmentUtils;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SingleLanguageDepartmentResponseMapper
        implements Function<Department, SingleLanguageDepartmentResponse> {

    private final MessageSourceService messageSourceService;

    @Override
    public SingleLanguageDepartmentResponse apply(Department department) {
        DepartmentDetails departmentDetails = DepartmentUtils.getDepartmentDetails(department);
        Set<String> roles = department.getRoles()
                .stream()
                .map(messageSourceService::getEmployeeRole)
                .collect(Collectors.toSet());

        return new SingleLanguageDepartmentResponse(
                department.getId(),
                departmentDetails.getName(),
                departmentDetails.getDescription(),
                departmentDetails.getAbbreviation(),
                roles
        );
    }
}
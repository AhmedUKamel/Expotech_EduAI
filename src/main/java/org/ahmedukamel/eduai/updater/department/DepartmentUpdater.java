package org.ahmedukamel.eduai.updater.department;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.department.UpdateDepartmentRequest;
import org.ahmedukamel.eduai.model.Department;
import org.ahmedukamel.eduai.model.DepartmentDetails;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.repository.DepartmentRepository;
import org.ahmedukamel.eduai.util.department.DepartmentUtils;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class DepartmentUpdater implements BiFunction<Department, UpdateDepartmentRequest, Department> {
    private final DepartmentRepository departmentRepository;

    @Override
    public Department apply(Department department, UpdateDepartmentRequest request) {
        DepartmentDetails departmentDetails_en = DepartmentUtils.getDepartmentDetails(department, Language.ENGLISH);
        DepartmentDetails departmentDetails_ar = DepartmentUtils.getDepartmentDetails(department, Language.ARABIC);
        DepartmentDetails departmentDetails_fr = DepartmentUtils.getDepartmentDetails(department, Language.FRENCH);

        departmentDetails_en.setName(request.name_en());
        departmentDetails_ar.setName(request.name_ar());
        departmentDetails_fr.setName(request.name_fr());

        return departmentRepository.save(department);
    }
}
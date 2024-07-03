package org.ahmedukamel.eduai.updater.department;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.department.UpdateDepartmentRequest;
import org.ahmedukamel.eduai.model.Department;
import org.ahmedukamel.eduai.model.DepartmentDetails;
import org.ahmedukamel.eduai.model.Employee;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.repository.DepartmentRepository;
import org.ahmedukamel.eduai.repository.EmployeeRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.util.department.DepartmentUtils;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class UpdateDepartmentRequestUpdater
        implements BiFunction<Department, UpdateDepartmentRequest, Department> {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public Department apply(Department department, UpdateDepartmentRequest request) {
        Employee head = DatabaseService.get(employeeRepository::findByIdAndSchool_Id,
                request.headId(), department.getSchool().getId(), Employee.class);

        department.setHead(head);

        DepartmentDetails departmentDetails_en = DepartmentUtils.getDepartmentDetails(department, Language.ENGLISH);
        DepartmentDetails departmentDetails_ar = DepartmentUtils.getDepartmentDetails(department, Language.ARABIC);
        DepartmentDetails departmentDetails_fr = DepartmentUtils.getDepartmentDetails(department, Language.FRENCH);

        departmentDetails_en.setName(request.name_en().strip());
        departmentDetails_ar.setName(request.name_ar().strip());
        departmentDetails_fr.setName(request.name_fr().strip());

        departmentDetails_en.setDescription(request.description_en().strip());
        departmentDetails_ar.setDescription(request.description_ar().strip());
        departmentDetails_fr.setDescription(request.description_fr().strip());

        departmentDetails_en.setAbbreviation(request.abbreviation_en().strip().toUpperCase());
        departmentDetails_ar.setAbbreviation(request.abbreviation_ar().strip().toUpperCase());
        departmentDetails_fr.setAbbreviation(request.abbreviation_fr().strip().toUpperCase());

        return departmentRepository.save(department);
    }
}
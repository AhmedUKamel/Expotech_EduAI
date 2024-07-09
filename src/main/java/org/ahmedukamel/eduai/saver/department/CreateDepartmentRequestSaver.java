package org.ahmedukamel.eduai.saver.department;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.department.CreateDepartmentRequest;
import org.ahmedukamel.eduai.model.Department;
import org.ahmedukamel.eduai.model.DepartmentDetails;
import org.ahmedukamel.eduai.model.Employee;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.repository.DepartmentRepository;
import org.ahmedukamel.eduai.repository.EmployeeRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class CreateDepartmentRequestSaver implements
        BiFunction<CreateDepartmentRequest, School, Department> {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public Department apply(CreateDepartmentRequest request, School school) {
        Department department = new Department();

        Employee head = DatabaseService.get(employeeRepository::findByIdAndSchool_Id,
                request.headId(), school.getId(), Employee.class);

        department.setHead(head);

        DepartmentDetails departmentDetails_en = DepartmentDetails
                .builder()
                .department(department)
                .language(Language.ENGLISH)
                .name(request.name_en().strip())
                .description(request.description_en().strip())
                .abbreviation(request.abbreviation_en().strip().toUpperCase())
                .build();

        DepartmentDetails departmentDetails_ar = DepartmentDetails
                .builder()
                .department(department)
                .language(Language.ARABIC)
                .name(request.name_ar().strip())
                .description(request.description_ar().strip())
                .abbreviation(request.abbreviation_ar().strip().toUpperCase())
                .build();

        DepartmentDetails departmentDetails_fr = DepartmentDetails
                .builder()
                .department(department)
                .language(Language.FRENCH)
                .name(request.name_fr().strip())
                .description(request.description_fr().strip())
                .abbreviation(request.abbreviation_fr().strip().toUpperCase())
                .build();


        department.setSchool(school);
        department.setDetails(Set.of(
                departmentDetails_en, departmentDetails_ar, departmentDetails_fr)
        );

        department.setRoles(request.roles());

        return departmentRepository.save(department);
    }
}
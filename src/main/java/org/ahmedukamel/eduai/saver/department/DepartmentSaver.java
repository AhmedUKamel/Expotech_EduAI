package org.ahmedukamel.eduai.saver.department;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.department.CreateDepartmentRequest;
import org.ahmedukamel.eduai.model.Department;
import org.ahmedukamel.eduai.model.DepartmentDetails;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.repository.DepartmentRepository;
import org.ahmedukamel.eduai.repository.SchoolRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class DepartmentSaver implements Function<CreateDepartmentRequest, Department> {
    private final DepartmentRepository departmentRepository;
    private final SchoolRepository schoolRepository;

    @Override
    public Department apply(CreateDepartmentRequest request) {
        School school = DatabaseService.get(schoolRepository::findById, request.schoolId(), School.class);

        Department department = new Department();

        DepartmentDetails departmentDetails_en = new DepartmentDetails(
                department, Language.ENGLISH, request.name_en().strip()
        ), departmentDetails_ar = new DepartmentDetails(
                department, Language.ARABIC, request.name_ar().strip()
        ), departmentDetails_fr = new DepartmentDetails(
                department, Language.FRENCH, request.name_fr().strip()
        );

        department.setSchool(school);
        department.setDetails(List.of(
                departmentDetails_en, departmentDetails_ar, departmentDetails_fr)
        );

        return departmentRepository.save(department);
    }
}
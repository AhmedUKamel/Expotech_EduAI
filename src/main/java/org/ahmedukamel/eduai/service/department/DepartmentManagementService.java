package org.ahmedukamel.eduai.service.department;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.department.AllLanguagesDepartmentResponse;
import org.ahmedukamel.eduai.dto.department.CreateDepartmentRequest;
import org.ahmedukamel.eduai.dto.department.SingleLanguageDepartmentResponse;
import org.ahmedukamel.eduai.dto.department.UpdateDepartmentRequest;
import org.ahmedukamel.eduai.mapper.department.AllLanguagesDepartmentResponseMapper;
import org.ahmedukamel.eduai.mapper.department.SingleLanguageDepartmentResponseMapper;
import org.ahmedukamel.eduai.model.Department;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.repository.DepartmentRepository;
import org.ahmedukamel.eduai.saver.department.CreateDepartmentRequestSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.updater.department.UpdateDepartmentRequestUpdater;
import org.ahmedukamel.eduai.util.context.ContextHolderUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentManagementService implements IDepartmentManagementService {
    private final SingleLanguageDepartmentResponseMapper singleLanguageDepartmentResponseMapper;
    private final AllLanguagesDepartmentResponseMapper allLanguagesDepartmentResponseMapper;
    private final UpdateDepartmentRequestUpdater updateDepartmentRequestUpdater;
    private final CreateDepartmentRequestSaver createDepartmentRequestSaver;
    private final DepartmentRepository departmentRepository;

    @Override
    public Object createDepartment(Object object) {
        CreateDepartmentRequest request = (CreateDepartmentRequest) object;
        School school = ContextHolderUtils.getEmployee().getSchool();

        Department savedDepartment = createDepartmentRequestSaver.apply(request, school);

        AllLanguagesDepartmentResponse response = allLanguagesDepartmentResponseMapper.apply(savedDepartment);
        String message = "Department created successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object updateDepartment(Integer id, Object object) {
        UpdateDepartmentRequest request = (UpdateDepartmentRequest) object;
        School school = ContextHolderUtils.getEmployee().getSchool();
        Department department = DatabaseService.get(departmentRepository::findByIdAndSchoolId,
                id, school.getId(), Department.class);

        Department updatedDepartment = updateDepartmentRequestUpdater.apply(department, request);

        AllLanguagesDepartmentResponse response = allLanguagesDepartmentResponseMapper.apply(updatedDepartment);
        String message = "Department updated successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object deleteDepartment(Integer id) {
        School school = ContextHolderUtils.getEmployee().getSchool();
        Department department = DatabaseService.get(departmentRepository::findByIdAndSchoolId,
                id, school.getId(), Department.class);
        try {
            departmentRepository.delete(department);
        } catch (DataIntegrityViolationException exception) {
            throw new RuntimeException("This department is associated with other records and can't be deleted.", exception);
        }

        String message = "Department deleted successfully.";

        return new ApiResponse(true, message, "");
    }

    @Override
    public Object getDepartment(Integer id) {
        School school = ContextHolderUtils.getEmployee().getSchool();
        Department department = DatabaseService.get(departmentRepository::findByIdAndSchoolId,
                id, school.getId(), Department.class);

        AllLanguagesDepartmentResponse response = allLanguagesDepartmentResponseMapper.apply(department);
        String message = "Department retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getAllDepartments(int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        School school = ContextHolderUtils.getEmployee().getSchool();

        Page<Department> departments = departmentRepository.findAllBySchoolId(school.getId(), pageable);

        Page<SingleLanguageDepartmentResponse> response = departments.map(singleLanguageDepartmentResponseMapper);
        String message = "All departments retrieved successfully.";

        return new ApiResponse(true, message, response);
    }
}
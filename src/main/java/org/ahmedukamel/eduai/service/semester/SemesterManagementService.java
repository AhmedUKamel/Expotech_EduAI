package org.ahmedukamel.eduai.service.semester;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.semester.CreateSemesterRequest;
import org.ahmedukamel.eduai.dto.semester.SemesterResponse;
import org.ahmedukamel.eduai.dto.semester.UpdateSemesterRequest;
import org.ahmedukamel.eduai.mapper.semester.SemesterResponseMapper;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.model.Semester;
import org.ahmedukamel.eduai.repository.SemesterRepository;
import org.ahmedukamel.eduai.saver.semester.SemesterSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.updater.semester.SemesterUpdater;
import org.ahmedukamel.eduai.util.context.ContextHolderUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SemesterManagementService implements ISemesterManagementService {
    private final SemesterResponseMapper semesterResponseMapper;
    private final SemesterRepository semesterRepository;
    private final SemesterUpdater semesterUpdater;
    private final SemesterSaver semesterSaver;

    @Override
    public Object createSemester(Object object) {
        School school = ContextHolderUtils.getEmployee().getSchool();
        CreateSemesterRequest request = (CreateSemesterRequest) object;

        Semester savedSemester = semesterSaver.apply(request, school);

        SemesterResponse response = semesterResponseMapper.apply(savedSemester);
        String message = "Semester created successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object updateSemester(Integer id, Object object) {
        School school = ContextHolderUtils.getEmployee().getSchool();
        Semester semester = DatabaseService.get(semesterRepository::findByIdAndSchool, id, school, Semester.class);
        UpdateSemesterRequest request = (UpdateSemesterRequest) object;

        Semester updatedSemester = semesterUpdater.apply(semester, request);

        SemesterResponse response = semesterResponseMapper.apply(updatedSemester);
        String message = "Semester updated successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object deleteSemester(Integer id) {
        School school = ContextHolderUtils.getEmployee().getSchool();
        Semester semester = DatabaseService.get(semesterRepository::findByIdAndSchool, id, school, Semester.class);

        try {
            semesterRepository.delete(semester);
        } catch (DataIntegrityViolationException exception) {
            throw new RuntimeException("This semester is associated with other records and can't be deleted.", exception);
        }

        String message = "Semester deleted successfully.";

        return new ApiResponse(true, message, "");
    }

    @Override
    public Object getSemester(Integer id) {
        School school = ContextHolderUtils.getEmployee().getSchool();
        Semester semester = DatabaseService.get(semesterRepository::findByIdAndSchool, id, school, Semester.class);

        SemesterResponse response = semesterResponseMapper.apply(semester);
        String message = "Semester retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getAllSemesters(int pageSize, int pageNumber) {
        School school = ContextHolderUtils.getEmployee().getSchool();
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Semester> semesters = semesterRepository.findAllBySchool(school, pageable);

        Page<SemesterResponse> response = semesters.map(semesterResponseMapper);
        String message = "All semesters retrieved successfully.";

        return new ApiResponse(true, message, response);
    }
}
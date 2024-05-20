package org.ahmedukamel.eduai.service.semester;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.semester.SemesterRequest;
import org.ahmedukamel.eduai.dto.semester.SemesterResponse;
import org.ahmedukamel.eduai.mapper.semester.SemesterResponseMapper;
import org.ahmedukamel.eduai.model.Semester;
import org.ahmedukamel.eduai.repository.SemesterRepository;
import org.ahmedukamel.eduai.saver.semester.SemesterSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.updater.semester.SemesterUpdater;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SemesterManagementService implements ISemesterManagementService {
    private final SemesterResponseMapper semesterResponseMapper;
    private final SemesterRepository semesterRepository;
    private final SemesterUpdater semesterUpdater;
    private final SemesterSaver semesterSaver;

    @Override
    public Object createSemester(Object object) {
        SemesterRequest request = (SemesterRequest) object;

        Semester savedSemester = semesterSaver.apply(request);

        SemesterResponse response = semesterResponseMapper.apply(savedSemester);
        String message = "Semester created successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object updateSemester(Integer id, Object object) {
        Semester semester = DatabaseService.get(semesterRepository::findById, id, Semester.class);
        SemesterRequest request = (SemesterRequest) object;

        Semester updatedSemester = semesterUpdater.apply(semester, request);

        SemesterResponse response = semesterResponseMapper.apply(updatedSemester);
        String message = "Semester updated successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object deleteSemester(Integer id) {
        Semester semester = DatabaseService.get(semesterRepository::findById, id, Semester.class);

        semesterRepository.delete(semester);

        String message = "Semester deleted successfully.";

        return new ApiResponse(true, message, "");
    }

    @Override
    public Object getSemester(Integer id) {
        Semester semester = DatabaseService.get(semesterRepository::findById, id, Semester.class);

        SemesterResponse response = semesterResponseMapper.apply(semester);
        String message = "Semester retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getAllSemesters(long pageSize, long pageNumber) {
        List<Semester> semesters = semesterRepository
                .selectSemestersWithPagination(pageSize, pageSize * (pageNumber - 1));

        List<SemesterResponse> response = semesters
                .stream()
                .map(semesterResponseMapper)
                .toList();
        String message = "Semester retrieved successfully.";

        return new ApiResponse(true, message, response);
    }
}
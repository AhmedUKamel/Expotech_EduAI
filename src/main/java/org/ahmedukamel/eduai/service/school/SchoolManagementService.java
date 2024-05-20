package org.ahmedukamel.eduai.service.school;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.school.CreateSchoolRequest;
import org.ahmedukamel.eduai.dto.school.SchoolResponse;
import org.ahmedukamel.eduai.dto.school.UpdateSchoolRequest;
import org.ahmedukamel.eduai.mapper.school.SchoolResponseMapper;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.repository.SchoolRepository;
import org.ahmedukamel.eduai.saver.school.SchoolSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.updater.school.SchoolUpdater;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolManagementService implements ISchoolManagementService {
    private final SchoolResponseMapper schoolResponseMapper;
    private final SchoolRepository schoolRepository;
    private final SchoolUpdater schoolUpdater;
    private final SchoolSaver schoolSaver;

    @Override
    public Object createSchool(Object object) {
        CreateSchoolRequest request = (CreateSchoolRequest) object;

        School savedSchool = schoolSaver.apply(request);

        SchoolResponse response = schoolResponseMapper.apply(savedSchool);
        String message = "School created successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object updateSchool(Integer id, Object object) {
        School school = DatabaseService.get(schoolRepository::findById, id, School.class);
        UpdateSchoolRequest request = (UpdateSchoolRequest) object;

        School updatedSchool = schoolUpdater.apply(school, request);

        SchoolResponse response = schoolResponseMapper.apply(updatedSchool);
        String message = "School updated successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object deleteSchool(Integer id) {
        School school = DatabaseService.get(schoolRepository::findById, id, School.class);

        schoolRepository.delete(school);

        String message = "School deleted successfully.";
        return new ApiResponse(true, message, "");
    }

    @Override
    public Object getSchool(Integer id) {
        School school = DatabaseService.get(schoolRepository::findById, id, School.class);

        SchoolResponse response = schoolResponseMapper.apply(school);
        String message = "School retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getAllSchools(long pageSize, long pageNumber) {
        List<School> schools = schoolRepository
                .selectSchoolsWithPagination(pageSize, pageSize * (pageNumber - 1));

        List<SchoolResponse> response = schools
                .stream()
                .map(schoolResponseMapper)
                .toList();
        String message = "Schools retrieved successfully.";

        return new ApiResponse(true, message, response);
    }
}
package org.ahmedukamel.eduai.service.public_;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.model.enumeration.*;
import org.ahmedukamel.eduai.service.message.MessageSourceService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnumerationService implements IEnumerationService {
    private final MessageSourceService messageSourceService;

    @Override
    public Object getNationalities() {
        Map<String, String> response = Arrays.stream(Nationality.values())
                .collect(Collectors.toMap(Nationality::name, messageSourceService::getNationality));
        String message = "Successful get all nationalities.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getReligions() {
        Map<String, String> response = Arrays.stream(Religion.values())
                .collect(Collectors.toMap(Religion::name, messageSourceService::getReligion));
        String message = "Successful get all religions.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getGenders() {
        Map<String, String> response = Arrays.stream(Gender.values())
                .collect(Collectors.toMap(Gender::name, messageSourceService::getGender));
        String message = "Successful get all genders.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getLabTypes() {
        Map<String, String> response = Arrays.stream(LabType.values())
                .collect(Collectors.toMap(LabType::name, messageSourceService::getLabType));
        String message = "Successful get all lab types.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getOfficeTypes() {
        Map<String, String> response = Arrays.stream(OfficeType.values())
                .collect(Collectors.toMap(OfficeType::name, messageSourceService::getOfficeType));
        String message = "Successful get all office types.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getRoomCategories() {
        Map<String, String> response = Arrays.stream(RoomCategory.values())
                .collect(Collectors.toMap(RoomCategory::name, messageSourceService::getRoomCategory));
        String message = "Successful get all room categories.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getRoomStatuses() {
        Map<String, String> response = Arrays.stream(RoomStatus.values())
                .collect(Collectors.toMap(RoomStatus::name, messageSourceService::getRoomStatus));
        String message = "Successful get all room statuses.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getRoomTypes() {
        Map<String, String> response = Arrays.stream(RoomType.values())
                .collect(Collectors.toMap(RoomType::name, messageSourceService::getRoomType));
        String message = "Successful get all room types.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getStudyLevels() {
        Map<String, String> response = Arrays.stream(StudyLevel.values())
                .collect(Collectors.toMap(StudyLevel::name, messageSourceService::getStudyLevel));
        String message = "Successful get all study levels.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getStudyStages() {
        Map<String, String> response = Arrays.stream(StudyStage.values())
                .collect(Collectors.toMap(StudyStage::name, messageSourceService::getStudyStage));
        String message = "Successful get all study stages.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getAttendanceStatuses() {
        Map<String, String> response = Arrays.stream(AttendanceStatus.values())
                .collect(Collectors.toMap(AttendanceStatus::name, messageSourceService::getAttendanceStatus));
        String message = "Successful get all attend status.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getExamResultStatuses() {
        Map<String, String> response = Arrays.stream(ExamResultStatus.values())
                .collect(Collectors.toMap(ExamResultStatus::name, messageSourceService::getExamResultStatus));
        String message = "Successful get all exam result status.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getAssociationTypes() {
        Map<String, String> response = Arrays.stream(AssociationType.values())
                .collect(Collectors.toMap(AssociationType::name, messageSourceService::getAssociationType));
        String message = "Successful get all association types.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getQualifications() {
        Map<String, String> response = Arrays.stream(Qualification.values())
                .collect(Collectors.toMap(Qualification::name, messageSourceService::getQualification));
        String message = "Successful get all qualifications.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getRoles() {
        Map<String, String> response = Arrays.stream(Role.values())
                .collect(Collectors.toMap(Role::name, messageSourceService::getRole));
        String message = "Successful get all roles.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getEmployeeTypes() {
        Map<String, String> response = Arrays.stream(EmployeeType.values())
                .collect(Collectors.toMap(EmployeeType::name, messageSourceService::getEmployeeType));
        String message = "Successful get all employee types.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getEmployeeStatuses() {
        Map<String, String> response = Arrays.stream(EmployeeStatus.values())
                .collect(Collectors.toMap(EmployeeStatus::name, messageSourceService::getEmployeeStatus));
        String message = "Successful get all employee statues.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getSubjects() {
        Map<String, String> response = Arrays.stream(Subject.values())
                .collect(Collectors.toMap(Subject::name, messageSourceService::getSubject));
        String message = "Successful get all subjects.";

        return new ApiResponse(true, message, response);
    }
}
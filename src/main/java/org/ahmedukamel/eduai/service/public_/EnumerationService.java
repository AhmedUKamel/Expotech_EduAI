package org.ahmedukamel.eduai.service.public_;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.constant.MessageSourceConstants;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.model.enumeration.*;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnumerationService implements IEnumerationService {
    private final MessageSource messageSource;

    @Override
    public Object getNationalities() {
        Function<Nationality, String> function = (i) -> messageSource.getMessage(
                MessageSourceConstants.ENUMERATION_NATIONALITY.formatted(i.name()),
                null, LocaleContextHolder.getLocale()
        );

        Map<String, String> response = Arrays.stream(Nationality.values())
                .collect(Collectors.toMap(Nationality::name, function));
        String message = "Successful get all nationalities.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getReligions() {
        Function<Religion, String> function = (i) -> messageSource.getMessage(
                MessageSourceConstants.ENUMERATION_RELIGION.formatted(i.name()),
                null, LocaleContextHolder.getLocale()
        );

        Map<String, String> response = Arrays.stream(Religion.values())
                .collect(Collectors.toMap(Religion::name, function));
        String message = "Successful get all religions.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getGenders() {
        Function<Gender, String> function = (i) -> messageSource.getMessage(
                MessageSourceConstants.ENUMERATION_GENDER.formatted(i.name()),
                null, LocaleContextHolder.getLocale()
        );

        Map<String, String> response = Arrays.stream(Gender.values())
                .collect(Collectors.toMap(Gender::name, function));
        String message = "Successful get all genders.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getLabTypes() {
        Function<LabType, String> function = (i) -> messageSource.getMessage(
                MessageSourceConstants.ENUMERATION_LAB_TYPE.formatted(i.name()),
                null, LocaleContextHolder.getLocale()
        );

        Map<String, String> response = Arrays.stream(LabType.values())
                .collect(Collectors.toMap(LabType::name, function));
        String message = "Successful get all lab types.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getOfficeTypes() {
        Function<OfficeType, String> function = (i) -> messageSource.getMessage(
                MessageSourceConstants.ENUMERATION_OFFICE_TYPE.formatted(i.name()),
                null, LocaleContextHolder.getLocale()
        );

        Map<String, String> response = Arrays.stream(OfficeType.values())
                .collect(Collectors.toMap(OfficeType::name, function));
        String message = "Successful get all office types.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getRoomCategories() {
        Function<RoomCategory, String> function = (i) -> messageSource.getMessage(
                MessageSourceConstants.ENUMERATION_ROOM_CATEGORY.formatted(i.name()),
                null, LocaleContextHolder.getLocale()
        );

        Map<String, String> response = Arrays.stream(RoomCategory.values())
                .collect(Collectors.toMap(RoomCategory::name, function));
        String message = "Successful get all room categories.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getRoomStatuses() {
        Function<RoomStatus, String> function = (i) -> messageSource.getMessage(
                MessageSourceConstants.ENUMERATION_ROOM_STATUS.formatted(i.name()),
                null, LocaleContextHolder.getLocale()
        );

        Map<String, String> response = Arrays.stream(RoomStatus.values())
                .collect(Collectors.toMap(RoomStatus::name, function));
        String message = "Successful get all room statuses.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getRoomTypes() {
        Function<RoomType, String> function = (i) -> messageSource.getMessage(
                MessageSourceConstants.ENUMERATION_ROOM_TYPE.formatted(i.name()),
                null, LocaleContextHolder.getLocale()
        );

        Map<String, String> response = Arrays.stream(RoomType.values())
                .collect(Collectors.toMap(RoomType::name, function));
        String message = "Successful get all room statuses.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getStudyLevels() {
        Function<StudyLevel, String> function = (i) -> messageSource.getMessage(
                MessageSourceConstants.ENUMERATION_STUDY_LEVEL.formatted(i.name()),
                null, LocaleContextHolder.getLocale()
        );

        Map<String, String> response = Arrays.stream(StudyLevel.values())
                .collect(Collectors.toMap(StudyLevel::name, function));
        String message = "Successful get all room statuses.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getStudyStages() {
        Function<StudyStage, String> function = (i) -> messageSource.getMessage(
                MessageSourceConstants.ENUMERATION_STUDY_STAGE.formatted(i.name()),
                null, LocaleContextHolder.getLocale()
        );

        Map<String, String> response = Arrays.stream(StudyStage.values())
                .collect(Collectors.toMap(StudyStage::name, function));
        String message = "Successful get all room statuses.";

        return new ApiResponse(true, message, response);
    }
}
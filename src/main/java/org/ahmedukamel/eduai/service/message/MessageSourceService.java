package org.ahmedukamel.eduai.service.message;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.constant.MessageSourceConstants;
import org.ahmedukamel.eduai.model.enumeration.*;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageSourceService {
    private final MessageSource messageSource;

    public String getLabType(LabType labType) {
        return messageSource.getMessage(
                MessageSourceConstants.ENUMERATION_LAB_TYPE.formatted(labType),
                null, LocaleContextHolder.getLocale());
    }

    public String getOfficeType(OfficeType officeType) {
        return messageSource.getMessage(
                MessageSourceConstants.ENUMERATION_OFFICE_TYPE.formatted(officeType),
                null, LocaleContextHolder.getLocale());
    }

    public String getRoomCategory(RoomCategory roomCategory) {
        return messageSource.getMessage(
                MessageSourceConstants.ENUMERATION_ROOM_CATEGORY.formatted(roomCategory),
                null, LocaleContextHolder.getLocale());
    }

    public String getRoomStatus(RoomStatus roomStatus) {
        return messageSource.getMessage(
                MessageSourceConstants.ENUMERATION_ROOM_STATUS.formatted(roomStatus),
                null, LocaleContextHolder.getLocale());
    }

    public String getRoomType(RoomType roomType) {
        return messageSource.getMessage(
                MessageSourceConstants.ENUMERATION_ROOM_TYPE.formatted(roomType),
                null, LocaleContextHolder.getLocale());
    }

    public String getStudyLevel(StudyLevel studyLevel) {
        return messageSource.getMessage(
                MessageSourceConstants.ENUMERATION_STUDY_LEVEL.formatted(studyLevel),
                null, LocaleContextHolder.getLocale());
    }

    public String getStudyStage(StudyStage studyStage) {
        return messageSource.getMessage(
                MessageSourceConstants.ENUMERATION_STUDY_STAGE.formatted(studyStage),
                null, LocaleContextHolder.getLocale());
    }

    public String getExamResultStatus(ExamResultStatus examResultStatus) {
        return messageSource.getMessage(
                MessageSourceConstants.ENUMERATION_EXAM_RESULT_STATUS.formatted(examResultStatus),
                null, LocaleContextHolder.getLocale());
    }

    public String getAttendanceStatus(AttendanceStatus attendanceStatus) {
        return messageSource.getMessage(
                MessageSourceConstants.ENUMERATION_ATTENDANCE_STATUS.formatted(attendanceStatus),
                null, LocaleContextHolder.getLocale());
    }

    public String getGender(Gender gender) {
        return messageSource.getMessage(
                MessageSourceConstants.ENUMERATION_GENDER.formatted(gender),
                null, LocaleContextHolder.getLocale());
    }

    public String getReligion(Religion religion) {
        return messageSource.getMessage(
                MessageSourceConstants.ENUMERATION_RELIGION.formatted(religion),
                null, LocaleContextHolder.getLocale());
    }

    public String getAssociationType(AssociationType associationType) {
        return messageSource.getMessage(
                MessageSourceConstants.ENUMERATION_ASSOCIATION_TYPE.formatted(associationType),
                null, LocaleContextHolder.getLocale());
    }

    public String getQualification(Qualification qualification) {
        return messageSource.getMessage(
                MessageSourceConstants.ENUMERATION_QUALIFICATION.formatted(qualification),
                null, LocaleContextHolder.getLocale());
    }

    public String getRole(Role role) {
        return messageSource.getMessage(
                MessageSourceConstants.ENUMERATION_ROLE.formatted(role),
                null, LocaleContextHolder.getLocale());
    }

    public String getNationality(Nationality nationality) {
        return messageSource.getMessage(
                MessageSourceConstants.ENUMERATION_NATIONALITY.formatted(nationality),
                null, LocaleContextHolder.getLocale());
    }
}
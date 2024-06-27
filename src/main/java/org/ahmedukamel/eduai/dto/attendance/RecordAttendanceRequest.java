package org.ahmedukamel.eduai.dto.attendance;

import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.eduai.annotation.ExistSection;
import org.ahmedukamel.eduai.annotation.ExistStudent;
import org.ahmedukamel.eduai.model.enumeration.AttendanceStatus;

public record RecordAttendanceRequest(
        @NotNull
        @ExistStudent
        Long studentId,

        @NotNull
        @ExistSection
        Integer sectionId,

        @NotNull
        AttendanceStatus status
) {
}
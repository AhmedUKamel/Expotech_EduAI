package org.ahmedukamel.eduai.dto.schedule;

import jakarta.validation.constraints.NotNull;
import org.ahmedukamel.eduai.model.enumeration.WeekDay;

import java.time.LocalTime;

public record CreateScheduleItemRequest(
        @NotNull
        Long classroomId,

        @NotNull
        Long courseId,

        @NotNull
        Long teacherId,

        @NotNull
        LocalTime startTime,

        @NotNull
        LocalTime endTime,

        @NotNull
        WeekDay day

        ) {
}

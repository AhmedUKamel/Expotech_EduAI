package org.ahmedukamel.eduai.dto.schedule;

import org.ahmedukamel.eduai.model.enumeration.WeekDay;

import java.time.LocalTime;

public record ScheduleItemResponseForTeacher(

        Long id,

        String classroomName,

        LocalTime startTime,

        LocalTime endTime,

        WeekDay day

        ) {
}

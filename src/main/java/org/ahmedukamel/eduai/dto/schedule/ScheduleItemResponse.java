package org.ahmedukamel.eduai.dto.schedule;

import org.ahmedukamel.eduai.model.enumeration.WeekDay;

import java.time.LocalTime;

public record ScheduleItemResponse(

        Long id,

        String courseName,

        String classroomName,

        String teacherName,

        LocalTime startTime,

        LocalTime endTime,

        WeekDay day

        ) {
}

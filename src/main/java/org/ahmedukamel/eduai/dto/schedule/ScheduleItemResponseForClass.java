package org.ahmedukamel.eduai.dto.schedule;

import org.ahmedukamel.eduai.model.enumeration.WeekDay;

import java.time.LocalTime;

public record ScheduleItemResponseForClass(

        Long id,

        String courseName,

        String teacherName,

        LocalTime startTime,

        LocalTime endTime,

        WeekDay day
        ) {
}

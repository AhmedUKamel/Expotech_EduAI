package org.ahmedukamel.eduai.mapper.schedule;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.schedule.ScheduleItemResponseForTeacher;
import org.ahmedukamel.eduai.model.ScheduleItem;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ScheduleItemTeacherResponseMapper implements Function<ScheduleItem, ScheduleItemResponseForTeacher> {
    @Override
    public ScheduleItemResponseForTeacher apply(ScheduleItem scheduleItem) {
        return new ScheduleItemResponseForTeacher(
                scheduleItem.getId(),
                scheduleItem.getClassroom().getName(),
                scheduleItem.getStartTime(),
                scheduleItem.getEndTime(),
                scheduleItem.getDay()
        );
    }
}

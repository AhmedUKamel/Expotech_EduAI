package org.ahmedukamel.eduai.mapper.schedule;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.schedule.ScheduleItemResponseForClass;
import org.ahmedukamel.eduai.dto.schedule.ScheduleItemResponseForTeacher;
import org.ahmedukamel.eduai.model.ScheduleItem;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ScheduleItemClassResponseMapper implements Function<ScheduleItem, ScheduleItemResponseForClass> {
    @Override
    public ScheduleItemResponseForClass apply(ScheduleItem scheduleItem) {
        return new  ScheduleItemResponseForClass(
                scheduleItem.getId(),
                scheduleItem.getCourse().getName(),
                scheduleItem.getTeacher().getUsername(),
                scheduleItem.getStartTime(),
                scheduleItem.getEndTime(),
                scheduleItem.getDay()
        );
    }
}

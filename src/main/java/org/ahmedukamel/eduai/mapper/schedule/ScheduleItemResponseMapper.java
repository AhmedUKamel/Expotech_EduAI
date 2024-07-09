package org.ahmedukamel.eduai.mapper.schedule;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.schedule.ScheduleItemResponse;
import org.ahmedukamel.eduai.model.ScheduleItem;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ScheduleItemResponseMapper implements Function<ScheduleItem, ScheduleItemResponse> {
    @Override
    public ScheduleItemResponse apply(ScheduleItem scheduleItem) {
        return new ScheduleItemResponse(
                scheduleItem.getId(),
//                scheduleItem.getCourse().getName(),
                "",
                scheduleItem.getClassroom().getName(),
                scheduleItem.getTeacher().getUsername(),
                scheduleItem.getStartTime(),
                scheduleItem.getEndTime(),
                scheduleItem.getDay()
        );
    }
}

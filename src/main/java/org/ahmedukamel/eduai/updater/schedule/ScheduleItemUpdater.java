package org.ahmedukamel.eduai.updater.schedule;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.schedule.UpdateScheduleItemRequest;
import org.ahmedukamel.eduai.model.ScheduleItem;
import org.ahmedukamel.eduai.model.Teacher;
import org.ahmedukamel.eduai.repository.ClassroomRepository;
import org.ahmedukamel.eduai.repository.ScheduleItemRepository;
import org.ahmedukamel.eduai.repository.TeacherRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class ScheduleItemUpdater implements BiFunction<ScheduleItem, UpdateScheduleItemRequest, ScheduleItem> {

    private final ScheduleItemRepository scheduleItemRepository;
    private final TeacherRepository teacherRepository;

    @Override
    public ScheduleItem apply(ScheduleItem scheduleItem, UpdateScheduleItemRequest request) {

        if(!scheduleItem.getTeacher().getId().equals(request.teacherId())){
            Teacher teacher = DatabaseService.get(teacherRepository::findById, request.teacherId(), Teacher.class);
            scheduleItem.setTeacher(teacher);
        }

        scheduleItem.setDay(request.day());
        scheduleItem.setStartTime(request.startTime());
        scheduleItem.setEndTime(request.endTime());

        return scheduleItemRepository.save(scheduleItem);
    }
}

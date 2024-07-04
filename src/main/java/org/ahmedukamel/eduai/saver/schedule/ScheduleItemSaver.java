package org.ahmedukamel.eduai.saver.schedule;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.schedule.CreateScheduleItemRequest;
import org.ahmedukamel.eduai.model.*;
import org.ahmedukamel.eduai.model.enumeration.RoomCategory;
import org.ahmedukamel.eduai.repository.ClassroomRepository;
import org.ahmedukamel.eduai.repository.CourseRepository;
import org.ahmedukamel.eduai.repository.ScheduleItemRepository;
import org.ahmedukamel.eduai.repository.TeacherRepository;
import org.ahmedukamel.eduai.saver.room.SpecialRoomSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ScheduleItemSaver implements Function<CreateScheduleItemRequest, ScheduleItem> {
    private final ScheduleItemRepository scheduleItemRepository;
    private final ClassroomRepository classroomRepository;
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    @Override
    public ScheduleItem apply(CreateScheduleItemRequest request) {
        Classroom classroom = DatabaseService.get(classroomRepository::findById, request.classroomId(), Classroom.class);
        Course course = DatabaseService.get(courseRepository::findById, request.courseId(), Course.class);
        Teacher teacher = DatabaseService.get(teacherRepository::findById, request.teacherId(), Teacher.class);

        ScheduleItem scheduleItem = ScheduleItem
                .builder()
                .classroom(classroom)
                .course(course)
                .teacher(teacher)
                .day(request.day())
                .startTime(request.startTime())
                .endTime(request.endTime())
                .build();

        return scheduleItemRepository.save(scheduleItem);
    }
}
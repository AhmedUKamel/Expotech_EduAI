package org.ahmedukamel.eduai.saver.room;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.room.classroom.CreateClassroomRequest;
import org.ahmedukamel.eduai.model.Classroom;
import org.ahmedukamel.eduai.model.Room;
import org.ahmedukamel.eduai.model.enumeration.RoomCategory;
import org.ahmedukamel.eduai.repository.ClassroomRepository;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ClassroomSaver implements Function<CreateClassroomRequest, Classroom> {
    private final ClassroomRepository classroomRepository;
    private final SpecialRoomSaver specialRoomSaver;

    @Override
    public Classroom apply(CreateClassroomRequest request) {
        Room room = specialRoomSaver.apply(request, RoomCategory.CLASSROOM);

        Classroom classroom = Classroom
                .builder()
                .name(request.classroomName().strip().toUpperCase())
                .number(request.classroomNumber())
                .level(request.studyLevel())
                .stage(request.studyStage())
                .room(room)
                .build();

        return classroomRepository.save(classroom);
    }
}
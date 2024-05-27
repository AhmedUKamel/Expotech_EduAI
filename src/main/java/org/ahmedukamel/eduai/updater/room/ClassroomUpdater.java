package org.ahmedukamel.eduai.updater.room;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.room.classroom.UpdateClassroomRequest;
import org.ahmedukamel.eduai.model.Classroom;
import org.ahmedukamel.eduai.model.Room;
import org.ahmedukamel.eduai.repository.ClassroomRepository;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class ClassroomUpdater implements BiFunction<Classroom, UpdateClassroomRequest, Classroom> {
    private final ClassroomRepository classroomRepository;
    private final SpecialRoomUpdater specialRoomUpdater;

    @Override
    public Classroom apply(Classroom classroom, UpdateClassroomRequest request) {
        Room updatedRoom = specialRoomUpdater.apply(classroom.getRoom(), request);

        classroom.setName(request.classroomName().strip().toUpperCase());
        classroom.setNumber(request.classroomNumber());
        classroom.setLevel(request.studyLevel());
        classroom.setStage(request.studyStage());
        classroom.setRoom(updatedRoom);

        return classroomRepository.save(classroom);
    }
}
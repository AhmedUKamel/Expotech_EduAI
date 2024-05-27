package org.ahmedukamel.eduai.mapper.room;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.room.classroom.ClassroomResponse;
import org.ahmedukamel.eduai.model.Classroom;
import org.ahmedukamel.eduai.service.message.MessageSourceService;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ClassroomResponseMapper implements Function<Classroom, ClassroomResponse> {
    private final MessageSourceService messageSourceService;

    @Override
    public ClassroomResponse apply(Classroom classroom) {
        return new ClassroomResponse(
                classroom.getRoom().getId(),
                classroom.getId(),
                classroom.getRoom().getBuildingNumber(),
                classroom.getRoom().getRoomNumber(),
                classroom.getRoom().getFloorNumber(),
                messageSourceService.getRoomType(classroom.getRoom().getType()),
                messageSourceService.getRoomStatus(classroom.getRoom().getStatus()),
                messageSourceService.getRoomCategory(classroom.getRoom().getCategory()),
                classroom.getRoom().getMaxCapacity(),
                classroom.getRoom().getSchool().getId(),
                classroom.getName(),
                classroom.getNumber(),
                messageSourceService.getStudyLevel(classroom.getLevel()),
                messageSourceService.getStudyStage(classroom.getStage())
        );
    }
}
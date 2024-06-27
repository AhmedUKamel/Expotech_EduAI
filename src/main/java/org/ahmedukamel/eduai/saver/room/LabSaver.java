package org.ahmedukamel.eduai.saver.room;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.room.lab.CreateLabRequest;
import org.ahmedukamel.eduai.model.Lab;
import org.ahmedukamel.eduai.model.Room;
import org.ahmedukamel.eduai.model.enumeration.RoomCategory;
import org.ahmedukamel.eduai.repository.LabRepository;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class LabSaver implements Function<CreateLabRequest, Lab> {
    private final LabRepository labRepository;
    private final SpecialRoomSaver specialRoomSaver;

    @Override
    public Lab apply(CreateLabRequest request) {
        Room room = specialRoomSaver.apply(request, RoomCategory.LAB);

        Lab lab = Lab
                .builder()
                .name(request.labName().strip().toUpperCase())
                .type(request.labType())
                .room(room)
                .build();

        return labRepository.save(lab);
    }
}
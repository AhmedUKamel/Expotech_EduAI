package org.ahmedukamel.eduai.updater.room;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.room.lab.UpdateLabRequest;
import org.ahmedukamel.eduai.model.Lab;
import org.ahmedukamel.eduai.model.Room;
import org.ahmedukamel.eduai.repository.LabRepository;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class LabUpdater implements BiFunction<Lab, UpdateLabRequest, Lab> {
    private final LabRepository labRepository;
    private final SpecialRoomUpdater specialRoomUpdater;

    @Override
    public Lab apply(Lab lab, UpdateLabRequest request) {
        Room updatedRoom = specialRoomUpdater.apply(lab.getRoom(), request);

        lab.setName(request.labName().strip().toUpperCase());
        lab.setType(request.labType());
        lab.setRoom(updatedRoom);

        return labRepository.save(lab);
    }
}
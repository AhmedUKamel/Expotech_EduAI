package org.ahmedukamel.eduai.updater.room;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.room.office.UpdateOfficeRequest;
import org.ahmedukamel.eduai.model.Office;
import org.ahmedukamel.eduai.model.Room;
import org.ahmedukamel.eduai.repository.OfficeRepository;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class OfficeUpdater implements BiFunction<Office, UpdateOfficeRequest, Office> {
    private final OfficeRepository officeRepository;
    private final SpecialRoomUpdater specialRoomUpdater;

    @Override
    public Office apply(Office office, UpdateOfficeRequest request) {
        Room updatedRoom = specialRoomUpdater.apply(office.getRoom(), request);

        office.setName(request.officeName().strip().toUpperCase());
        office.setType(request.officeType());
        office.setRoom(updatedRoom);

        return officeRepository.save(office);
    }
}
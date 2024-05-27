package org.ahmedukamel.eduai.saver.room;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.room.office.CreateOfficeRequest;
import org.ahmedukamel.eduai.model.Office;
import org.ahmedukamel.eduai.model.Room;
import org.ahmedukamel.eduai.model.enumeration.RoomCategory;
import org.ahmedukamel.eduai.repository.OfficeRepository;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class OfficeSaver implements Function<CreateOfficeRequest, Office> {
    private final OfficeRepository officeRepository;
    private final SpecialRoomSaver specialRoomSaver;

    @Override
    public Office apply(CreateOfficeRequest request) {
        Room room = specialRoomSaver.apply(request, RoomCategory.LAB);

        Office office = Office
                .builder()
                .name(request.officeName().strip().toUpperCase())
                .type(request.officeType())
                .room(room)
                .build();

        return officeRepository.save(office);
    }
}
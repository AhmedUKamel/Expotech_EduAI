package org.ahmedukamel.eduai.saver.room;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.room.CreateRoomRequest;
import org.ahmedukamel.eduai.model.Room;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.model.enumeration.RoomStatus;
import org.ahmedukamel.eduai.repository.RoomRepository;
import org.ahmedukamel.eduai.repository.SchoolRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class RoomSaver implements Function<CreateRoomRequest, Room> {
    private final SchoolRepository schoolRepository;
    private final RoomRepository roomRepository;

    @Override
    public Room apply(CreateRoomRequest request) {
        DatabaseService.unique(roomRepository::existsBySchool_IdAndRoomNumberIgnoreCase,
                request.schoolId(), request.roomNumber().strip(), Room.class);

        School school = DatabaseService.get(schoolRepository::findById, request.schoolId(), School.class);

        Room room = Room
                .builder()
                .school(school)
                .buildingNumber(request.buildingNumber().strip().toUpperCase())
                .roomNumber(request.roomNumber().strip().toUpperCase())
                .floorNumber(request.floorNumber())
                .type(request.type())
                .category(request.category())
                .status(RoomStatus.AVAILABLE)
                .maxCapacity(request.maxCapacity())
                .build();

        return roomRepository.save(room);
    }
}
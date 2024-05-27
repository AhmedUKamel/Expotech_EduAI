package org.ahmedukamel.eduai.updater.room;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.room.special.IUpdateSpecialRoomRequest;
import org.ahmedukamel.eduai.model.Room;
import org.ahmedukamel.eduai.repository.RoomRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class SpecialRoomUpdater implements BiFunction<Room, IUpdateSpecialRoomRequest, Room> {
    private final RoomRepository roomRepository;

    @Override
    public Room apply(Room room, IUpdateSpecialRoomRequest request) {
        if (!room.getRoomNumber().equalsIgnoreCase(request.roomNumber().strip())) {
            DatabaseService.unique(roomRepository::existsBySchool_IdAndRoomNumberIgnoreCase,
                    room.getSchool().getId(), request.roomNumber().strip(), Room.class);
        }

        room.setBuildingNumber(request.buildingNumber().strip().toUpperCase());
        room.setRoomNumber(request.roomNumber().strip().toUpperCase());
        room.setFloorNumber(request.floorNumber());
        room.setType(request.type());
        room.setStatus(request.status());
        room.setMaxCapacity(request.maxCapacity());

        return roomRepository.save(room);
    }
}
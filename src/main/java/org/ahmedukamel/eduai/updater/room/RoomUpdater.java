package org.ahmedukamel.eduai.updater.room;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.room.UpdateRoomRequest;
import org.ahmedukamel.eduai.model.Room;
import org.ahmedukamel.eduai.repository.RoomRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class RoomUpdater implements BiFunction<Room, UpdateRoomRequest, Room> {
    private final RoomRepository roomRepository;

    @Override
    public Room apply(Room room, UpdateRoomRequest request) {
        String roomNumber = request.roomNumber().strip();

        if (!room.getRoomNumber().equalsIgnoreCase(roomNumber)) {
            DatabaseService.unique(roomRepository::existsBySchool_IdAndRoomNumberIgnoreCase,
                    room.getSchool().getId(), roomNumber, Room.class);
        }

        room.setBuildingNumber(request.buildingNumber().strip().toUpperCase());
        room.setRoomNumber(roomNumber.toUpperCase());
        room.setFloorNumber(request.floorNumber());
        room.setType(request.type());
        room.setCategory(request.category());
        room.setStatus(request.status());
        room.setMaxCapacity(request.maxCapacity());

        return roomRepository.save(room);
    }
}
package org.ahmedukamel.eduai.service.room;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.room.CreateRoomRequest;
import org.ahmedukamel.eduai.dto.room.RoomResponse;
import org.ahmedukamel.eduai.dto.room.UpdateRoomRequest;
import org.ahmedukamel.eduai.mapper.room.RoomResponseMapper;
import org.ahmedukamel.eduai.model.Room;
import org.ahmedukamel.eduai.model.enumeration.RoomCategory;
import org.ahmedukamel.eduai.repository.RoomRepository;
import org.ahmedukamel.eduai.saver.room.RoomSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.updater.room.RoomUpdater;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomManagementService implements IRoomManagementService {
    private final RoomResponseMapper roomResponseMapper;
    private final RoomRepository roomRepository;
    private final RoomUpdater roomUpdater;
    private final RoomSaver roomSaver;

    @Override
    public Object createRoom(Object object) {
        CreateRoomRequest request = (CreateRoomRequest) object;

        Room savedRoom = roomSaver.apply(request);

        RoomResponse response = roomResponseMapper.apply(savedRoom);
        String message = "Room created successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object updateRoom(Long id, Object object) {
        Room room = DatabaseService.get(roomRepository::findById, id, Room.class);
        UpdateRoomRequest request = (UpdateRoomRequest) object;

        Room updatedRoom = roomUpdater.apply(room, request);

        RoomResponse response = roomResponseMapper.apply(updatedRoom);
        String message = "Room updated successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object deleteRoom(Long id) {
        Room room = DatabaseService.get(roomRepository::findById, id, Room.class);

        roomRepository.delete(room);

        String message = "Room deleted successfully.";

        return new ApiResponse(true, message, "");
    }

    @Override
    public Object getRoom(Long id) {
        Room room = DatabaseService.get(roomRepository::findById, id, Room.class);

        RoomResponse response = roomResponseMapper.apply(room);
        String message = "Room retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getAllRooms(long pageSize, long pageNumber) {
        List<Room> rooms = roomRepository
                .selectRoomsWithPagination(pageSize, pageSize * (pageNumber - 1));

        List<RoomResponse> response = rooms
                .stream()
                .map(roomResponseMapper)
                .toList();
        String message = "Rooms retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getAllRoomsByCategory(RoomCategory category, long pageSize, long pageNumber) {
        List<Room> rooms = roomRepository
                .selectRoomsByCategoryWithPagination(category, pageSize, pageSize * (pageNumber - 1));

        List<RoomResponse> response = rooms
                .stream()
                .map(roomResponseMapper)
                .toList();
        String message = "Rooms retrieved successfully.";

        return new ApiResponse(true, message, response);
    }
}
package org.ahmedukamel.eduai.service.room;

import org.ahmedukamel.eduai.model.enumeration.RoomCategory;

public interface IRoomManagementService {
    Object createRoom(Object object);

    Object updateRoom(Long id, Object object);

    Object deleteRoom(Long id);

    Object getRoom(Long id);

    Object getAllRooms(long pageSize, long pageNumber);

    Object getAllRoomsByCategory(RoomCategory category, long pageSize, long pageNumber);
}
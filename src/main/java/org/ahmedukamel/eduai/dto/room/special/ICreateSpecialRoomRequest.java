package org.ahmedukamel.eduai.dto.room.special;

import org.ahmedukamel.eduai.model.enumeration.RoomType;

public interface ICreateSpecialRoomRequest {
    String buildingNumber();

    String roomNumber();

    Integer floorNumber();

    RoomType type();

    Integer maxCapacity();

    Integer schoolId();
}
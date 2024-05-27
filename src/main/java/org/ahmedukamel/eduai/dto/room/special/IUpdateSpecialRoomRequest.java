package org.ahmedukamel.eduai.dto.room.special;

import org.ahmedukamel.eduai.model.enumeration.RoomStatus;
import org.ahmedukamel.eduai.model.enumeration.RoomType;

public interface IUpdateSpecialRoomRequest {
    String buildingNumber();

    String roomNumber();

    Integer floorNumber();

    RoomType type();

    RoomStatus status();

    Integer maxCapacity();
}
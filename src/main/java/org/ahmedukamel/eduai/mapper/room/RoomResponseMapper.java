package org.ahmedukamel.eduai.mapper.room;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.room.RoomResponse;
import org.ahmedukamel.eduai.model.Room;
import org.ahmedukamel.eduai.service.message.MessageSourceService;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class RoomResponseMapper implements Function<Room, RoomResponse> {
    private final MessageSourceService messageSourceService;

    @Override
    public RoomResponse apply(Room room) {
        return new RoomResponse(
                room.getId(),
                room.getBuildingNumber(),
                room.getRoomNumber(),
                room.getFloorNumber(),
                room.getMaxCapacity(),
                room.getSchool().getId(),
                messageSourceService.getRoomType(room.getType()),
                messageSourceService.getRoomStatus(room.getStatus()),
                messageSourceService.getRoomCategory(room.getCategory()),
                room.getCreatedAt(),
                room.getUpdatedAt()
        );
    }
}
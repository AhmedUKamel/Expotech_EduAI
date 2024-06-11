package org.ahmedukamel.eduai.mapper.bus;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.bus.BusResponse;
import org.ahmedukamel.eduai.dto.room.RoomResponse;
import org.ahmedukamel.eduai.model.Bus;
import org.ahmedukamel.eduai.model.Room;
import org.ahmedukamel.eduai.service.message.MessageSourceService;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class BusResponseMapper implements Function<Bus, BusResponse> {

    @Override
    public BusResponse apply(Bus bus) {
        return new BusResponse(
                bus.getId(),
                bus.getBusNumber(),
                bus.getDriverName(),
                bus.getDriverNumber(),
                bus.getBusCapacity(),
                bus.getRoute(),
                bus.getSchool().getId(),
                bus.getCreatedAt(),
                bus.getUpdatedAt()
        );
    }
}
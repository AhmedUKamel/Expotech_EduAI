package org.ahmedukamel.eduai.mapper.room;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.room.office.OfficeResponse;
import org.ahmedukamel.eduai.model.Office;
import org.ahmedukamel.eduai.service.message.MessageSourceService;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class OfficeResponseMapper implements Function<Office, OfficeResponse> {
    private final MessageSourceService messageSourceService;

    @Override
    public OfficeResponse apply(Office office) {
        return new OfficeResponse(
                office.getRoom().getId(),
                office.getId(),
                office.getRoom().getBuildingNumber(),
                office.getRoom().getRoomNumber(),
                office.getRoom().getFloorNumber(),
                messageSourceService.getRoomType(office.getRoom().getType()),
                messageSourceService.getRoomStatus(office.getRoom().getStatus()),
                messageSourceService.getRoomCategory(office.getRoom().getCategory()),
                office.getRoom().getMaxCapacity(),
                office.getRoom().getSchool().getId(),
                office.getName(),
                messageSourceService.getOfficeType(office.getType())
        );
    }
}
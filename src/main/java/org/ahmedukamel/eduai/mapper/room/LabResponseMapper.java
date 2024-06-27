package org.ahmedukamel.eduai.mapper.room;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.room.lab.LabResponse;
import org.ahmedukamel.eduai.model.Lab;
import org.ahmedukamel.eduai.service.message.MessageSourceService;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class LabResponseMapper implements Function<Lab, LabResponse> {
    private final MessageSourceService messageSourceService;

    @Override
    public LabResponse apply(Lab lab) {
        return new LabResponse(
                lab.getRoom().getId(),
                lab.getId(),
                lab.getRoom().getBuildingNumber(),
                lab.getRoom().getRoomNumber(),
                lab.getRoom().getFloorNumber(),
                messageSourceService.getRoomType(lab.getRoom().getType()),
                messageSourceService.getRoomStatus(lab.getRoom().getStatus()),
                messageSourceService.getRoomCategory(lab.getRoom().getCategory()),
                lab.getRoom().getMaxCapacity(),
                lab.getRoom().getSchool().getId(),
                lab.getName(),
                messageSourceService.getLabType(lab.getType())
        );
    }
}
package org.ahmedukamel.eduai.mapper.position;

import org.ahmedukamel.eduai.dto.position.SingleLanguagePositionResponse;
import org.ahmedukamel.eduai.model.Position;
import org.ahmedukamel.eduai.model.PositionDetails;
import org.ahmedukamel.eduai.util.position.PositionUtils;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SingleLanguagePositionResponseMapper
        implements Function<Position, SingleLanguagePositionResponse> {

    @Override
    public SingleLanguagePositionResponse apply(Position position) {
        PositionDetails positionDetails = PositionUtils.getPositionDetails(position);

        return new SingleLanguagePositionResponse(
                position.getId(),
                positionDetails.getTitle(),
                position.getCreatedAt(),
                position.getUpdatedAt(),
                position.getDepartment().getId()
        );
    }
}
package org.ahmedukamel.eduai.updater.position;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.position.UpdatePositionRequest;
import org.ahmedukamel.eduai.model.Position;
import org.ahmedukamel.eduai.model.PositionDetails;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.repository.PositionRepository;
import org.ahmedukamel.eduai.util.position.PositionUtils;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class PositionUpdater implements BiFunction<Position, UpdatePositionRequest, Position> {
    private final PositionRepository positionRepository;

    @Override
    public Position apply(Position position, UpdatePositionRequest request) {
        PositionDetails positionDetails_en = PositionUtils.getPositionDetails(position, Language.ENGLISH);
        PositionDetails positionDetails_ar = PositionUtils.getPositionDetails(position, Language.ARABIC);
        PositionDetails positionDetails_fr = PositionUtils.getPositionDetails(position, Language.FRENCH);

        positionDetails_en.setTitle(request.title_en().strip());
        positionDetails_ar.setTitle(request.title_ar().strip());
        positionDetails_fr.setTitle(request.title_fr().strip());

        return positionRepository.save(position);
    }
}
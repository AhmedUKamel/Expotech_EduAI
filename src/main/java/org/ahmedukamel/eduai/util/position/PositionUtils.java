package org.ahmedukamel.eduai.util.position;

import org.ahmedukamel.eduai.model.Position;
import org.ahmedukamel.eduai.model.PositionDetails;
import org.ahmedukamel.eduai.model.enumeration.Language;

public class PositionUtils {
    public static PositionDetails getPositionDetails(Position position, Language language) {
        return position.getDetails()
                .stream()
                .filter(positionDetails -> positionDetails.getLanguage().equals(language))
                .findFirst()
                .orElseThrow();
    }
}
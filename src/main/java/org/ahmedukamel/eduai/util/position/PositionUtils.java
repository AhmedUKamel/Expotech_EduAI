package org.ahmedukamel.eduai.util.position;

import org.ahmedukamel.eduai.model.Position;
import org.ahmedukamel.eduai.model.PositionDetails;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.lang.NonNull;

public abstract class PositionUtils {
    public static PositionDetails getPositionDetails(@NonNull Position position, @NonNull Language language) {
        return position.getDetails()
                .stream()
                .filter(positionDetails -> positionDetails.getLanguage().equals(language))
                .findFirst()
                .orElseThrow();
    }

    public static PositionDetails getPositionDetails(@NonNull Position position) {
        return position.getDetails()
                .stream()
                .filter(positionDetails -> positionDetails.getLanguage().getCode()
                        .equalsIgnoreCase(LocaleContextHolder.getLocale().getLanguage()))
                .findFirst()
                .orElseThrow();
    }
}
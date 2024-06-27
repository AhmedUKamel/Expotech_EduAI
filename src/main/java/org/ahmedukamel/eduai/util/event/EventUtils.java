package org.ahmedukamel.eduai.util.event;

import org.ahmedukamel.eduai.model.Event;
import org.ahmedukamel.eduai.model.EventDetail;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.springframework.context.i18n.LocaleContextHolder;

public class EventUtils {
    public static EventDetail getEventDetail(Event event) {
        return getEventDetail(event, LocaleContextHolder.getLocale().getLanguage());
    }

    public static EventDetail getEventDetail(Event event, Language language) {
        return getEventDetail(event, language.getCode());
    }

    public static EventDetail getEventDetail(Event event, String languageCode) {
        return event.getDetails()
                .stream()
                .filter(eventDetail -> eventDetail.getLanguage().getCode()
                        .equalsIgnoreCase(languageCode.strip()))
                .findFirst()
                .orElseThrow();
    }
}
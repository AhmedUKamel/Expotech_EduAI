package org.ahmedukamel.eduai.constant;

import java.util.List;
import java.util.Locale;

public interface LocaleConstants {
    Locale ENGLISH = new Locale("en");
    Locale ARABIC = new Locale("ar");
    Locale FRENCH = new Locale("fr");

    List<Locale> SUPPORTED_LOCALES = List.of(ENGLISH, ARABIC, FRENCH);
}
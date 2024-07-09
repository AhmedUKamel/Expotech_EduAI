package org.ahmedukamel.eduai.util.language;

import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.util.context.ContextHolderUtils;

import java.util.Collection;

public abstract class LanguageUtils {
    public static <T extends ILanguage> T getDetails(Collection<T> objects, Language language) {
        return objects
                .stream()
                .filter(obj -> obj.getLanguage().equals(language))
                .findFirst()
                .orElseThrow();
    }

    public static <T extends ILanguage> T getDetails(Collection<T> objects) {
        String languageCode = ContextHolderUtils.getLanguage();

        return objects
                .stream()
                .filter(obj -> obj.getLanguage().getCode().equalsIgnoreCase(languageCode))
                .findFirst()
                .orElseThrow();
    }
}
package org.ahmedukamel.eduai.util.parent;

import org.ahmedukamel.eduai.model.Parent;
import org.ahmedukamel.eduai.model.ParentDetail;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.NoSuchElementException;

public abstract class ParentUtils {
    public static ParentDetail getParentDetail(Parent parent) {
        String languageCode = LocaleContextHolder.getLocale().getLanguage();

        return parent.getParentDetails()
                .stream()
                .filter(userDetail -> userDetail.getLanguage().getCode()
                        .equalsIgnoreCase(languageCode))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                        "Parent details with language %s not found.".formatted(languageCode)));
    }

    public static ParentDetail getParentDetail(Parent parent, Language language) {
        String languageCode = language.getCode();

        return parent.getParentDetails()
                .stream()
                .filter(userDetail -> userDetail.getLanguage().getCode()
                        .equalsIgnoreCase(languageCode))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                        "Parent details with language %s not found.".formatted(languageCode)));
    }
}
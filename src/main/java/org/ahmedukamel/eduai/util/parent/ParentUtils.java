package org.ahmedukamel.eduai.util.parent;

import org.ahmedukamel.eduai.model.Parent;
import org.ahmedukamel.eduai.model.ParentDetail;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.NoSuchElementException;

public abstract class ParentUtils {
    public static ParentDetail getParentDetail(Parent parent) {
        String languageCode = LocaleContextHolder.getLocale().getLanguage();

        return parent.getDetails()
                .stream()
                .filter(userDetail -> userDetail.getLanguage().getCode()
                        .equalsIgnoreCase(languageCode))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                        "Parent details with language %s not found.".formatted(languageCode)));
    }
}
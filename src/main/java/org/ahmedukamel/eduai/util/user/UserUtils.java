package org.ahmedukamel.eduai.util.user;

import org.ahmedukamel.eduai.model.User;
import org.ahmedukamel.eduai.model.UserDetail;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.NoSuchElementException;

public abstract class UserUtils {
    public static UserDetail getUserDetail(User user) {
        String languageCode = LocaleContextHolder.getLocale().getLanguage();

        return user.getDetails()
                .stream()
                .filter(userDetail -> userDetail.getLanguage().getCode()
                        .equalsIgnoreCase(languageCode))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                        "User details with language %s not found.".formatted(languageCode)));
    }
}
package org.ahmedukamel.eduai.mapper.profile;

import org.ahmedukamel.eduai.model.User;
import org.ahmedukamel.eduai.model.UserDetail;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.function.Predicate;

public abstract class UserProfileResponseMapper {
    public final UserDetail getDetails(User user) {
        Predicate<UserDetail> filter = (i) -> i.getLanguage().getCode()
                .equalsIgnoreCase(LocaleContextHolder.getLocale().getLanguage());

        return user.getDetails()
                .stream()
                .filter(filter)
                .findFirst()
                .orElseThrow();
    }
}
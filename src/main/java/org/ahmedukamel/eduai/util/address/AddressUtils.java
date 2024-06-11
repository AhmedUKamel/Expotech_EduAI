package org.ahmedukamel.eduai.util.address;

import org.ahmedukamel.eduai.model.Region;
import org.springframework.context.i18n.LocaleContextHolder;

public abstract class AddressUtils {
    public static String formatAddress(Region region) {
        return "%s, %s, %s".formatted(
                getLocalizedName(region.getCity().getCountry()),
                getLocalizedName(region.getCity()),
                getLocalizedName(region)
        );
    }

    public static String getLocalizedName(INameLocalization object) {
        final String languageCode = LocaleContextHolder.getLocale().getLanguage();

        if (languageCode.equalsIgnoreCase("ar")) {
            return object.getName_ar();
        } else if (languageCode.equalsIgnoreCase("fr")) {
            return object.getName_fr();
        } else {
            return object.getName_en();
        }
    }
}
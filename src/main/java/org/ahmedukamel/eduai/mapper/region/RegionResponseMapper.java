package org.ahmedukamel.eduai.mapper.region;

import org.ahmedukamel.eduai.constant.LocaleConstants;
import org.ahmedukamel.eduai.dto.region.RegionResponse;
import org.ahmedukamel.eduai.model.Region;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;
import java.util.function.Function;

public class RegionResponseMapper implements Function<Region, RegionResponse> {
    @Override
    public RegionResponse apply(Region region) {
        final String name;
        Locale currentLocale = LocaleContextHolder.getLocale();

        if (currentLocale.equals(LocaleConstants.ARABIC)) {
            name = region.getName_ar();
        } else if (currentLocale.equals(LocaleConstants.FRENCH)) {
            name = region.getName_fr();
        } else {
            name = region.getName_en();
        }

        return new RegionResponse(
                region.getId(),
                name,
                region.getLocation().getLatitude(),
                region.getLocation().getLongitude(),
                region.getZipCode(),
                region.getCity().getId()
        );
    }
}
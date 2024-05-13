package org.ahmedukamel.eduai.dto.region;

public record RegionResponse(
        Integer id,

        String name,

        Double latitude,

        Double longitude,

        Integer zipCode,

        Integer cityId
) {
}
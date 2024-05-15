package org.ahmedukamel.eduai.mapper.profile;

import org.ahmedukamel.eduai.dto.profile.ParentProfileResponse;
import org.ahmedukamel.eduai.model.Parent;
import org.ahmedukamel.eduai.model.ParentDetail;
import org.ahmedukamel.eduai.model.UserDetail;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.function.Function;
import java.util.function.Predicate;

@Component
public class ParentProfileResponseMapper extends UserProfileResponseMapper
        implements Function<Parent, ParentProfileResponse> {
    @Override
    public ParentProfileResponse apply(Parent parent) {
        UserDetail userDetail = super.getDetails(parent.getUser());
        ParentDetail teacherDetail = this.getDetails(parent);

        return new ParentProfileResponse(
                parent.getUser().getUsername(),
                parent.getUser().getEmail(),
                parent.getUser().getPicture(),
                StringUtils.hasLength(parent.getUser().getPicture()),
                parent.getUser().getNid(),
                parent.getUser().getGender(),
                parent.getUser().getNationality(),
                parent.getUser().getRegion().getId(),
                parent.getPhoneNumber().toString(),
                userDetail.getName().getFirst(),
                userDetail.getName().getLast(),
                userDetail.getAbout(),
                parent.getPhoneNumber().toString(),
                teacherDetail.getOccupation()
        );
    }

    private ParentDetail getDetails(Parent parent) {
        Predicate<ParentDetail> filter = (i) -> i.getLanguage().getCode()
                .equalsIgnoreCase(LocaleContextHolder.getLocale().getLanguage());

        return parent.getDetails()
                .stream()
                .filter(filter)
                .findFirst()
                .orElseThrow();
    }
}
package org.ahmedukamel.eduai.mapper.profile;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.profile.ParentProfileResponse;
import org.ahmedukamel.eduai.model.Parent;
import org.ahmedukamel.eduai.model.ParentDetail;
import org.ahmedukamel.eduai.model.UserDetail;
import org.ahmedukamel.eduai.service.message.MessageSourceService;
import org.ahmedukamel.eduai.util.user.UserUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.function.Function;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
public class ParentProfileResponseMapper
        implements Function<Parent, ParentProfileResponse> {

    private final MessageSourceService messageSourceService;

    @Override
    public ParentProfileResponse apply(Parent parent) {
        UserDetail userDetail = UserUtils.getUserDetail(parent);
        ParentDetail teacherDetail = this.getDetails(parent);

        return new ParentProfileResponse(
                parent.getId(),
                parent.getUsername(),
                parent.getEmail(),
                parent.getPicture(),
                StringUtils.hasLength(parent.getPicture()),
                parent.getNid(),
                messageSourceService.getGender(parent.getGender()),
                messageSourceService.getRole(parent.getRole()),
                messageSourceService.getNationality(parent.getNationality()),
                messageSourceService.getReligion(parent.getReligion()),
                parent.getRegion().getId(),
                parent.getBirthDate(),
                parent.getPhoneNumber().toString(),
                userDetail.getName(),
                parent.getAbout(),
                parent.getPhoneNumber().toString(),
                teacherDetail.getOccupation()
        );
    }

    private ParentDetail getDetails(Parent parent) {
        Predicate<ParentDetail> filter = (i) -> i.getLanguage().getCode()
                .equalsIgnoreCase(LocaleContextHolder.getLocale().getLanguage());

        return parent.getParentDetails()
                .stream()
                .filter(filter)
                .findFirst()
                .orElseThrow();
    }
}
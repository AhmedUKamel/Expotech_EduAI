package org.ahmedukamel.eduai.mapper.user_management;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.user_management.AdminParentResponse;
import org.ahmedukamel.eduai.model.Parent;
import org.ahmedukamel.eduai.model.ParentDetail;
import org.ahmedukamel.eduai.model.User;
import org.ahmedukamel.eduai.model.UserDetail;
import org.ahmedukamel.eduai.service.message.MessageSourceService;
import org.ahmedukamel.eduai.util.address.AddressUtils;
import org.ahmedukamel.eduai.util.parent.ParentUtils;
import org.ahmedukamel.eduai.util.user.UserUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class AdminParentResponseMapper implements Function<Parent, AdminParentResponse> {
    private final MessageSourceService messageSourceService;

    @Override
    public AdminParentResponse apply(Parent parent) {
        User user = parent.getUser();
        UserDetail userDetails = UserUtils.getUserDetail(user);
        ParentDetail parentDetails = ParentUtils.getParentDetail(parent);

        return new AdminParentResponse(
                userDetails.getName().toString(),
                parent.getId(),
                user.getId(),
                messageSourceService.getGender(user.getGender()),
                messageSourceService.getReligion(user.getReligion()),
                parentDetails.getOccupation(),
                AddressUtils.formatAddress(user.getRegion()),
                parent.getPhoneNumber().toString(),
                user.getEmail(),
                // TODO: Map picture url
                user.getPicture(),
                StringUtils.hasLength(user.getPicture()),
                ((int) ChronoUnit.YEARS.between(user.getBirthDate(), LocalDateTime.now())),
                0,
                user.getBirthDate()

        );
    }
}
package org.ahmedukamel.eduai.saver.parent;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.parent.IParentRegistrationRequest;
import org.ahmedukamel.eduai.mapper.phonenumber.PhoneNumberMapper;
import org.ahmedukamel.eduai.mapper.user.UserRegistrationRequestMapper;
import org.ahmedukamel.eduai.model.Parent;
import org.ahmedukamel.eduai.model.ParentDetail;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.model.embeddable.PhoneNumber;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.repository.ParentRepository;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class IParentRegistrationRequestSaver
        implements BiFunction<IParentRegistrationRequest, School, Parent> {

    private final UserRegistrationRequestMapper<Parent> userRegistrationRequestMapper;
    private final PhoneNumberMapper phoneNumberMapper;
    private final ParentRepository parentRepository;

    @Override
    public Parent apply(IParentRegistrationRequest request, School school) {
        PhoneNumber phoneNumber = phoneNumberMapper.apply(request.number());

        Parent parent = userRegistrationRequestMapper.apply(request, Parent.class);

        parent.setPhoneNumber(phoneNumber);
        parent.setSchool(school);

        ParentDetail parentDetail_en = new ParentDetail();
        parentDetail_en.setParent(parent);
        parentDetail_en.setLanguage(Language.ENGLISH);
        parentDetail_en.setOccupation(request.occupation_en().strip());

        ParentDetail parentDetail_ar = new ParentDetail();
        parentDetail_ar.setParent(parent);
        parentDetail_ar.setLanguage(Language.ARABIC);
        parentDetail_ar.setOccupation(request.occupation_ar().strip());

        ParentDetail parentDetail_fr = new ParentDetail();
        parentDetail_fr.setParent(parent);
        parentDetail_fr.setLanguage(Language.FRENCH);
        parentDetail_fr.setOccupation(request.occupation_fr().strip());

        parent.setParentDetails(Set.of(
                parentDetail_en,
                parentDetail_ar,
                parentDetail_fr
        ));

        return parentRepository.save(parent);
    }
}
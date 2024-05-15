package org.ahmedukamel.eduai.saver.auth;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.auth.ParentRegistrationRequest;
import org.ahmedukamel.eduai.mapper.phonenumber.PhoneNumberMapper;
import org.ahmedukamel.eduai.model.Parent;
import org.ahmedukamel.eduai.model.ParentDetail;
import org.ahmedukamel.eduai.model.User;
import org.ahmedukamel.eduai.model.embeddable.PhoneNumber;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.model.enumeration.Role;
import org.ahmedukamel.eduai.repository.ParentRepository;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ParentSaver implements Function<ParentRegistrationRequest, Parent> {
    private final PhoneNumberMapper phoneNumberMapper;
    private final ParentRepository parentRepository;
    private final UserSaver userSaver;

    @Override
    public Parent apply(ParentRegistrationRequest request) {
        PhoneNumber phoneNumber = phoneNumberMapper.apply(request.number());

        User savedUser = userSaver.apply(request, Role.PARENT);

        Parent parent = Parent
                .builder()
                .phoneNumber(phoneNumber)
                .user(savedUser)
                .build();

        ParentDetail parentDetail_en = ParentDetail
                .builder()
                .occupation(request.occupation_en())
                .parent(parent)
                .language(Language.ENGLISH)
                .build(),

                parentDetail_ar = ParentDetail
                        .builder()
                        .occupation(request.occupation_ar())
                        .parent(parent)
                        .language(Language.ARABIC)
                        .build(),

                parentDetail_fr = ParentDetail
                        .builder()
                        .occupation(request.occupation_fr())
                        .parent(parent)
                        .language(Language.FRENCH)
                        .build();

        parent.setDetails(Set.of(parentDetail_en, parentDetail_ar, parentDetail_fr));
        return parentRepository.save(parent);
    }
}
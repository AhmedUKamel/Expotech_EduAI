package org.ahmedukamel.eduai.updater.parent;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.parent.IParentRegistrationRequest;
import org.ahmedukamel.eduai.dto.parent.UpdateParentRequest;
import org.ahmedukamel.eduai.mapper.phonenumber.PhoneNumberMapper;
import org.ahmedukamel.eduai.mapper.user.UserRegistrationRequestMapper;
import org.ahmedukamel.eduai.model.*;
import org.ahmedukamel.eduai.model.embeddable.PhoneNumber;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.model.enumeration.Role;
import org.ahmedukamel.eduai.repository.ParentRepository;
import org.ahmedukamel.eduai.repository.RegionRepository;
import org.ahmedukamel.eduai.repository.UserRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.util.parent.ParentUtils;
import org.ahmedukamel.eduai.util.user.UserUtils;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class ParentRequestUpdater
        implements BiFunction<Parent, UpdateParentRequest, Parent> {

    private final UserRegistrationRequestMapper<Parent> userRegistrationRequestMapper;
    private final PhoneNumberMapper phoneNumberMapper;
    private final ParentRepository parentRepository;
    private final UserRepository userRepository;
    private final RegionRepository regionRepository;

    @Override
    public Parent apply(Parent parent, UpdateParentRequest request) {
        PhoneNumber phoneNumber = phoneNumberMapper.apply(request.number());
        if(parent.getPhoneNumber().getCountryCode() != phoneNumber.getCountryCode()||
            parent.getPhoneNumber().getNationalNumber() != phoneNumber.getNationalNumber()){
            if(parentRepository.existsByPhoneNumber(phoneNumber)){
                throw new RuntimeException("Phone Number is already in use");
            }
            parent.setPhoneNumber(phoneNumber);
        }
        if(!parent.getEmail().equals(request.email())){
            if(userRepository.existsByEmailIgnoreCase(request.email())){
                throw new RuntimeException("Email is already in use");
            }
            parent.setEmail(request.email());
        }
        if(!parent.getNid().equals(request.nid())){
            if(userRepository.existsByNid(request.nid())){
                throw new RuntimeException("Nid is already in use");
            }
            parent.setNid(request.nid());
        }
        if(!parent.getUsername().equals(request.username())){
            if(userRepository.existsByUsernameIgnoreCase(request.username())){
                throw new RuntimeException("Username is already in use");
            }
            parent.setUsername(request.username());
        }

        if(!parent.getRegion().getId().equals(request.regionId())){
            Region region = DatabaseService.get(regionRepository::findById, request.regionId(), Region.class);
            parent.setRegion(region);
        }

        parent.setRole(Role.PARENT);
        parent.setAbout(request.about());
        parent.setBirthDate(request.birthDate());
        parent.setGender(request.gender());
        parent.setNationality(request.nationality());
        parent.setReligion(request.religion());

        UserDetail userDetail_en = UserUtils.getUserDetail(parent, Language.ENGLISH);
        userDetail_en.setName(request.name_en());

        UserDetail userDetail_ar = UserUtils.getUserDetail(parent, Language.ARABIC);
        userDetail_ar.setName(request.name_ar());

        UserDetail userDetail_fr = UserUtils.getUserDetail(parent, Language.FRENCH);
        userDetail_fr.setName(request.name_fr());

        ParentDetail parentDetail_en = ParentUtils.getParentDetail(parent, Language.ENGLISH);
        parentDetail_en.setOccupation(request.occupation_en().strip());

        ParentDetail parentDetail_ar = ParentUtils.getParentDetail(parent, Language.ARABIC);
        parentDetail_ar.setOccupation(request.occupation_ar().strip());

        ParentDetail parentDetail_fr = ParentUtils.getParentDetail(parent, Language.FRENCH);
        parentDetail_fr.setOccupation(request.occupation_fr().strip());


        return parentRepository.save(parent);
    }
}
package org.ahmedukamel.eduai.updater.teacher;

import org.ahmedukamel.eduai.dto.teacher.UpdateTeacherRequest;
import org.ahmedukamel.eduai.mapper.phonenumber.PhoneNumberMapper;
import org.ahmedukamel.eduai.model.Teacher;
import org.ahmedukamel.eduai.model.UserDetail;
import org.ahmedukamel.eduai.model.embeddable.PhoneNumber;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.repository.RegionRepository;
import org.ahmedukamel.eduai.repository.TeacherRepository;
import org.ahmedukamel.eduai.repository.UserRepository;
import org.ahmedukamel.eduai.util.user.UserUtils;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component

public class UpdateTeacherUpdater implements BiFunction<Teacher, UpdateTeacherRequest, Teacher> {

    private final TeacherRepository teacherRepository;
    private final RegionRepository regionRepository;
    private final PhoneNumberMapper phoneNumberMapper;
    private final UserRepository userRepository;

    public UpdateTeacherUpdater(TeacherRepository teacherRepository, RegionRepository regionRepository, PhoneNumberMapper phoneNumberMapper, UserRepository userRepository) {
        this.teacherRepository = teacherRepository;
        this.regionRepository = regionRepository;
        this.phoneNumberMapper = phoneNumberMapper;
        this.userRepository = userRepository;
    }

    @Override
    public Teacher apply(Teacher teacher, UpdateTeacherRequest request) {
        PhoneNumber phoneNumber = phoneNumberMapper.apply(request.number());

        if(teacher.getPhoneNumber().getCountryCode() != phoneNumber.getCountryCode()||
            teacher.getPhoneNumber().getNationalNumber() != phoneNumber.getNationalNumber()){
            if(teacherRepository.existsByPhoneNumber(phoneNumber)){
                throw new RuntimeException("Phone Number is already in use");
            }
            teacher.setPhoneNumber(phoneNumber);
        }

        if(!teacher.getRegion().getId().equals(request.regionId())){
            teacher.setRegion(regionRepository.findById(request.regionId()).orElseThrow());
        }
        if(!teacher.getEmail().equals(request.email())){
            if(userRepository.existsByEmailIgnoreCase(request.email())){
                throw new RuntimeException("Email is already in use");
            }
            teacher.setEmail(request.email());
        }
        if(!teacher.getNid().equals(request.nid())){
            if(userRepository.existsByNid(request.nid())){
                throw new RuntimeException("Nid is already in use");
            }
            teacher.setNid(request.nid());
        }
        if(!teacher.getUsername().equals(request.username())){
            if(userRepository.existsByUsernameIgnoreCase(request.username())){
                throw new RuntimeException("Username is already in use");
            }
            teacher.setUsername(request.username());
        }

        teacher.setCreatedDate(request.createdDate());
        teacher.setUpdatedDate(request.updatedDate());

        teacher.setQualification(request.qualification());
        teacher.setSubject(request.subject());
        teacher.setBirthDate(request.birthDate());
        teacher.setReligion(request.religion());
        teacher.setGender(request.gender());
        teacher.setNationality(request.nationality());
        teacher.setAbout(request.about());
        teacher.setQualification(request.qualification());

        UserDetail userDetail_en = UserUtils.getUserDetail(teacher, Language.ENGLISH);
        userDetail_en.setName(request.name_en());

        UserDetail userDetail_ar = UserUtils.getUserDetail(teacher, Language.ARABIC);
        userDetail_ar.setName(request.name_ar());

        UserDetail userDetail_fr = UserUtils.getUserDetail(teacher, Language.FRENCH);
        userDetail_fr.setName(request.name_fr());
        return teacherRepository.save(teacher);
    }
}

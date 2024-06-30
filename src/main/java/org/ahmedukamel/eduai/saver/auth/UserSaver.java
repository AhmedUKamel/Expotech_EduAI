package org.ahmedukamel.eduai.saver.auth;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.auth.IUserRegistrationRequest;
import org.ahmedukamel.eduai.model.Region;
import org.ahmedukamel.eduai.model.User;
import org.ahmedukamel.eduai.model.UserDetail;
import org.ahmedukamel.eduai.model.embeddable.Name;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.model.enumeration.Role;
import org.ahmedukamel.eduai.repository.RegionRepository;
import org.ahmedukamel.eduai.repository.UserRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class UserSaver implements BiFunction<IUserRegistrationRequest, Role, User> {
    private final RegionRepository regionRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public User apply(IUserRegistrationRequest request, Role role) {
        Region region = DatabaseService.get(regionRepository::findById, request.regionId(), Region.class);
        String password = passwordEncoder.encode(request.password());

        User user = new User();

        user.setUsername(request.username().strip());
        user.setEmail(request.email().strip().toLowerCase());
        user.setPassword(password);
        user.setGender(request.gender());
        user.setRole(role);
        user.setNid(request.nid());
        user.setBirthDate(request.birthDate());
        user.setNationality(request.nationality());
        user.setRegion(region);
        user.setReligion(request.religion());
        user.setEnabled(true); // Temporary TODO: Send Activation Email
        user.setAccountNonLocked(true);


        UserDetail userDetail_en = UserDetail
                .builder()
                .name(new Name(request.firstName_en(), request.lastName_en()))
                .about(request.about_en())
                .user(user)
                .language(Language.ENGLISH)
                .build(),

                userDetail_ar = UserDetail
                        .builder()
                        .name(new Name(request.firstName_ar(), request.lastName_ar()))
                        .about(request.about_ar())
                        .user(user)
                        .language(Language.ARABIC)
                        .build(),

                userDetail_fr = UserDetail
                        .builder()
                        .name(new Name(request.firstName_fr(), request.lastName_fr()))
                        .about(request.about_fr())
                        .user(user)
                        .language(Language.FRENCH)
                        .build();

        user.setDetails(Set.of(userDetail_en, userDetail_ar, userDetail_fr));
        return userRepository.save(user);
    }
}
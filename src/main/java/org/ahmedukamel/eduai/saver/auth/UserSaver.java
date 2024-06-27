package org.ahmedukamel.eduai.saver.auth;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.auth.UserRegistrationRequest;
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
public class UserSaver implements BiFunction<UserRegistrationRequest, Role, User> {
    private final RegionRepository regionRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public User apply(UserRegistrationRequest request, Role role) {
        Region region = DatabaseService.get(regionRepository::findById, request.regionId(), Region.class);
        String password = passwordEncoder.encode(request.password());

        User user = User
                .builder()
                .username(request.username().strip())
                .email(request.email().strip().toLowerCase())
                .password(password)
                .gender(request.gender())
                .role(role)
                .nid(request.nid())
                .birthDate(request.birthDate())
                .nationality(request.nationality())
                .region(region)
                .religion(request.religion())
                .enabled(true) // Temporary TODO: Send Activation Email
                .accountNonLocked(true)
                .build();

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
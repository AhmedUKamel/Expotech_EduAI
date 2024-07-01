package org.ahmedukamel.eduai.mapper.user;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.auth.IUserRegistrationRequest;
import org.ahmedukamel.eduai.model.Region;
import org.ahmedukamel.eduai.model.User;
import org.ahmedukamel.eduai.model.UserDetail;
import org.ahmedukamel.eduai.model.embeddable.Name;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.repository.RegionRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class UserRegistrationRequestMapper<T extends User>
        implements BiFunction<IUserRegistrationRequest, Class<? extends T>, T> {

    private final RegionRepository regionRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public T apply(IUserRegistrationRequest request, Class<? extends T> tClass) {
        try {
            T user = tClass.getDeclaredConstructor().newInstance();

            Region region = DatabaseService.get(regionRepository::findById, request.regionId(), Region.class);
            String password = passwordEncoder.encode(request.password());

            user.setUsername(request.username().strip());
            user.setEmail(request.email().strip().toLowerCase());
            user.setNid(request.nid().strip());
            user.setBirthDate(request.birthDate());

            user.setPassword(password);
            user.setRegion(region);

            user.setGender(request.gender());
            user.setNationality(request.nationality());
            user.setReligion(request.religion());

            user.setAccountNonLocked(true);

            UserDetail userDetail_en = UserDetail.builder()
                    .name(new Name(request.firstName_en(), request.lastName_en()))
                    .about(request.about_en())
                    .user(user)
                    .language(Language.ENGLISH)
                    .build();

            UserDetail userDetail_ar = UserDetail.builder()
                    .name(new Name(request.firstName_ar(), request.lastName_ar()))
                    .about(request.about_ar())
                    .user(user)
                    .language(Language.ARABIC)
                    .build();

            UserDetail userDetail_fr = UserDetail.builder()
                    .name(new Name(request.firstName_fr(), request.lastName_fr()))
                    .about(request.about_fr())
                    .user(user)
                    .language(Language.FRENCH)
                    .build();

            user.setUserDetails(Set.of(userDetail_en, userDetail_ar, userDetail_fr));

            return user;
        } catch (Exception exception) {
            throw new RuntimeException("Failed to create user instance", exception);
        }
    }
}
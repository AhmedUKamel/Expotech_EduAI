package org.ahmedukamel.eduai.service.access_token;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.model.AccessToken;
import org.ahmedukamel.eduai.model.User;
import org.ahmedukamel.eduai.repository.AccessTokenRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.service.jwt.JwtService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AccessTokenService implements IAccessTokenService {
    private final AccessTokenRepository repository;
    private final JwtService jwtService;

    @Override
    public AccessToken create(User user) {
        String token = jwtService.generateToken(user);
        Date expiration = jwtService.getExpiration(token);

        AccessToken accessToken = AccessToken
                .builder()
                .token(token)
                .expiration(expiration)
                .user(user)
                .build();

        return repository.save(accessToken);
    }

    @Override
    public AccessToken get(String token) {
        return DatabaseService.get(repository::findById, token, AccessToken.class);
    }

    @Override
    public boolean exists(String token) {
        return repository.existsById(token);
    }

    @Override
    public void delete(String token) {
        AccessToken accessToken = DatabaseService.get(repository::findById, token, AccessToken.class);
        repository.delete(accessToken);
    }
}
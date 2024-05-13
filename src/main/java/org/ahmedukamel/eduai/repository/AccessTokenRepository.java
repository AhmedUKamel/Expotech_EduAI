package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.AccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessTokenRepository extends JpaRepository<AccessToken, String> {
}
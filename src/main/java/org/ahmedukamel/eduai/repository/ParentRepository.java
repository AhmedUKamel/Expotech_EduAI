package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.Parent;
import org.ahmedukamel.eduai.model.embeddable.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {
    boolean existsByPhoneNumber(PhoneNumber phoneNumber);
}
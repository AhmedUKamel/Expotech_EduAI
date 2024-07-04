package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.Parent;
import org.ahmedukamel.eduai.model.embeddable.PhoneNumber;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {
    boolean existsByPhoneNumber(PhoneNumber phoneNumber);

    Optional<Parent> findByIdAndSchool_Id(Long id, Integer schoolId);

    Page<Parent> findAllBySchool_IdAndAccountNonLocked(Integer schoolId, boolean accountNonLocked, Pageable pageable);
}
package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.Teacher;
import org.ahmedukamel.eduai.model.embeddable.PhoneNumber;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    boolean existsByPhoneNumber(PhoneNumber phoneNumber);

    Optional<Teacher> findByIdAndSchool_Id(Long aLong, Integer schoolId);

    Page<Teacher> findAllBySchool_IdAndAccountNonLocked(Integer schoolId, boolean accountNonLocked, Pageable pageable);
}
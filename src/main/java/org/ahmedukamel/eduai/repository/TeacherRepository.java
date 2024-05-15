package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.Teacher;
import org.ahmedukamel.eduai.model.embeddable.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    boolean existsByNid(String nid);

    boolean existsByPhoneNumber(PhoneNumber phoneNumber);
}
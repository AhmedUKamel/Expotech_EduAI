package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.model.Semester;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Integer> {
    Page<Semester> findAllBySchool(School school, Pageable pageable);

    Optional<Semester> findByIdAndSchool(Integer id, School school);
}
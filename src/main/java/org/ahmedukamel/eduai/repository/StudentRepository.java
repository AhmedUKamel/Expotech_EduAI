package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Page<Student> findAllBySchool_Id(Integer schoolId, Pageable pageable);

    Optional<Student> findByIdAndSchool_Id(Long id, Integer schoolId);
}
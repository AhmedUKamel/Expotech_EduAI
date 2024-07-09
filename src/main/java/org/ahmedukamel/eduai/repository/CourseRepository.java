package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByIdAndSchool_Id(Long id, Integer schoolId);

    Page<Course> findAllBySchool_Id(Integer schoolId, Pageable pageable);

    boolean existsBySchool_IdAndCodeIgnoreCase(Integer schoolId, String code);
}
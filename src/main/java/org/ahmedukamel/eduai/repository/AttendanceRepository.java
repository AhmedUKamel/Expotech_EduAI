package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.Attendance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Page<Attendance> findAllByStudent_Id(Long studentId, Pageable pageable);

    Page<Attendance> findAllBySection_Id(Integer sectionId, Pageable pageable);

    boolean existsByStudent_IdAndSection_Id(Long studentId, Integer sectionId);
}
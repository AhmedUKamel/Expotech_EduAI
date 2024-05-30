package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.ExamResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamResultRepository extends JpaRepository<ExamResult, Long> {
    Page<ExamResult> findAllByExam_Id(Long examId, Pageable pageable);

    Page<ExamResult> findAllByStudent_Id(Long studentId, Pageable pageable);

    boolean existsByExam_IdAndStudent_Id(Long examId, Long studentId);
}
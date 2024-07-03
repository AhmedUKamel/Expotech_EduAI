package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.TrainingProgram;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainingProgramRepository extends JpaRepository<TrainingProgram,Long> {
    Optional<TrainingProgram> findByIdAndSchool_Id(Long aLong, Integer integer);
    Page<TrainingProgram> findAllBySchool_Id(Integer id, Pageable pageable);
}

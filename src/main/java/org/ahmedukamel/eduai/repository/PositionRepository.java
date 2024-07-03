package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.Position;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {
    Optional<Position> findByIdAndDepartment_School_Id(Integer id, Integer schoolId);

    Page<Position> findAllByDepartment_School_Id(Integer id, Pageable pageable);
}
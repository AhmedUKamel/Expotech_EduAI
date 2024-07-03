package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Optional<Department> findByIdAndSchoolId(Integer id, Integer schoolId);

    Page<Department> findAllBySchoolId(Integer schoolId, Pageable pageable);
}
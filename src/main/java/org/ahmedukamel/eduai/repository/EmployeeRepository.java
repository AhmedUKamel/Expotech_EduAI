package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.Employee;
import org.ahmedukamel.eduai.model.embeddable.PhoneNumber;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByPhoneNumber(PhoneNumber phoneNumber);

    Page<Employee> findAllByPositionIsNullAndSchool_Id(Integer schoolId, Pageable pageable);

    Page<Employee> findAllBySchool_Id(Integer schoolId, Pageable pageable);

    Optional<Employee> findByIdAndSchool_Id(Long id, Integer schoolId);
}
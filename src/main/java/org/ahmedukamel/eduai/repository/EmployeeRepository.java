package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.Employee;
import org.ahmedukamel.eduai.model.embeddable.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByPhoneNumber(PhoneNumber phoneNumber);
}
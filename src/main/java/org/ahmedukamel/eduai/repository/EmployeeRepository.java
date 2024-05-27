package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.Employee;
import org.ahmedukamel.eduai.model.embeddable.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    boolean existsByPhoneNumber(PhoneNumber phoneNumber);

    @Query(value = """
            SELECT e
            FROM Employee e
            WHERE e.position is null
            ORDER BY e.id
            """)
    List<Employee> selectUnEmployedEmployeesWithPagination(@Param("limit") long limit,
                                                           @Param("offset") long offset);
}
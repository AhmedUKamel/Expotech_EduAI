package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.Department;
import org.ahmedukamel.eduai.model.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    @Query(value = """
            SELECT d
            FROM Department d
            ORDER BY d.id
            LIMIT :limit
            OFFSET :offset""")
    List<Department> selectDepartmentsWithPagination(@Param("limit") long limit,
                                                     @Param("offset") long offset);

    @Query(value = """
            SELECT d
            FROM Department d
            WHERE d.school = :school
            ORDER BY d.id
            LIMIT :limit
            OFFSET :offset""")
    List<Department> selectSchoolDepartmentsWithPagination(@Param(value = "school") School school,
                                                           @Param("limit") long limit,
                                                           @Param("offset") long offset);
}
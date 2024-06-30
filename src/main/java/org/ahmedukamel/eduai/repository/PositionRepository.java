package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.Department;
import org.ahmedukamel.eduai.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {
    @Query(value = """
            SELECT p
            FROM Position p
            ORDER BY p.id
            LIMIT :limit
            OFFSET :offset""")
    List<Position> selectPositionsWithPagination(@Param("limit") long limit,
                                                 @Param("offset") long offset);

    @Query(value = """
            SELECT p
            FROM Position p
            WHERE p.department = :department
            ORDER BY p.id
            LIMIT :limit
            OFFSET :offset""")
    List<Position> selectPositionsByDepartmentWithPagination(@Param(value = "department") Department department,
                                                             @Param("limit") long limit,
                                                             @Param("offset") long offset);

    Optional<Position> findByIdAndDepartment_School_Id(Integer id, Integer schoolId);
}
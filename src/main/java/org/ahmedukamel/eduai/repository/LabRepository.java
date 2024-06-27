package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.Lab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabRepository extends JpaRepository<Lab, Long> {
    @Query(value = """
            SELECT l
            FROM Lab l
            ORDER BY l.id
            LIMIT :limit
            OFFSET :offset""")
    List<Lab> selectLabsWithPagination(@Param("limit") long limit, @Param("offset") long offset);
}
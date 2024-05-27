package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    @Query(value = """
            SELECT c
            FROM Classroom c
            ORDER BY c.id
            LIMIT :limit
            OFFSET :offset""")
    List<Classroom> selectClassroomsWithPagination(@Param("limit") long limit, @Param("offset") long offset);
}
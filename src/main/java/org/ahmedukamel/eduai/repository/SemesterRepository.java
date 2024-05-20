package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Integer> {
    @Query(value = """
            SELECT s
            FROM Semester s
            ORDER BY s.id
            LIMIT :limit
            OFFSET :offset""")
    List<Semester> selectSemestersWithPagination(@Param("limit") long limit,
                                                 @Param("offset") long offset);
}
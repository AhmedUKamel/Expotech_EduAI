package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    @Query(value = """
            SELECT e
            FROM Exam e
            ORDER BY e.id
            LIMIT :limit
            OFFSET :offset""")
    List<Exam> selectExamsWithPagination(@Param("limit") long limit,
                                         @Param("offset") long offset);
}
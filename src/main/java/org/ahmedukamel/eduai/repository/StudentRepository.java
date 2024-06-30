package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.Exam;
import org.ahmedukamel.eduai.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(value = """
            SELECT e
            FROM Student e
            ORDER BY e.id
            LIMIT :limit
            OFFSET :offset""")
    List<Student> selectStudentsWithPagination(@Param("limit") long limit,
                                         @Param("offset") long offset);
}
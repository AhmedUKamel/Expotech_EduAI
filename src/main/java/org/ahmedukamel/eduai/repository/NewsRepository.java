package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.Exam;
import org.ahmedukamel.eduai.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsRepository extends JpaRepository<News,Integer> {
    @Query(value = """
            SELECT e
            FROM News e
            ORDER BY e.id
            LIMIT :limit
            OFFSET :offset""")
    List<News> selectNewsWithPagination(@Param("limit") long limit,
                                         @Param("offset") long offset);

}

package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolRepository extends JpaRepository<School, Integer> {
    @Query(value = """
            SELECT s
            FROM School s
            ORDER BY s.id
            LIMIT :limit
            OFFSET :offset""")
    List<School> selectSchoolsWithPagination(@Param(value = "limit") long limit,
                                             @Param(value = "offset") long offset);

    boolean existsByNameIgnoreCase(String name);

    boolean existsByCodeIgnoreCase(String code);
}
package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfficeRepository extends JpaRepository<Office, Long> {
    @Query(value = """
            SELECT o
            FROM Office o
            ORDER BY o.id
            LIMIT :limit
            OFFSET :offset""")
    List<Office> selectOfficesWithPagination(@Param("limit") long limit, @Param("offset") long offset);
}
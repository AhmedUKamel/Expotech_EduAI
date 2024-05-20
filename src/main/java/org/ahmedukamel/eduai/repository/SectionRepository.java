package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionRepository extends JpaRepository<Section, Integer> {
    @Query(value = """
            SELECT s
            FROM Section s
            ORDER BY s.id
            LIMIT :limit
            OFFSET :offset""")
    List<Section> selectSectionsWithPagination(@Param("limit") long limit,
                                               @Param("offset") long offset);
}
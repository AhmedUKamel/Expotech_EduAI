package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Class, Integer> {
    @Query(value = """
            SELECT c
            FROM Class c
            ORDER BY c.id
            LIMIT :limit
            OFFSET :offset""")
    List<Class> selectClassesWithPagination(@Param("limit") long limit,
                                            @Param("offset") long offset);

    boolean existsBySchool_IdAndNameIgnoreCase(Integer schoolId, String name);

    boolean existsBySchool_IdAndGroupIgnoreCase(Integer schoolId, String group);

    boolean existsBySchool_IdAndNumberIgnoreCase(Integer schoolId, String number);
}
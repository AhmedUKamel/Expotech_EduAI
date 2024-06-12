package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.Bus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    boolean existsBySchool_IdAndBusNumberIgnoreCase(Integer id, String busNumber);

    @Query(value = """
            SELECT b
            FROM Bus b
            WHERE b.school.id = :schoolId
            ORDER BY b.id
            LIMIT :limit
            OFFSET :offset""")
    List<Bus> selectBusesBySchoolIdWithPagination(@Param("schoolId") Integer schoolId, @Param("limit") long limit, @Param("offset") long offset);

    Page<Bus> findAllBySchoolId(Integer schoolId, Pageable pageable);
}
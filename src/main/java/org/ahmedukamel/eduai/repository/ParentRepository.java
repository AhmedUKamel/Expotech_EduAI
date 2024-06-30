package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.Exam;
import org.ahmedukamel.eduai.model.Parent;
import org.ahmedukamel.eduai.model.embeddable.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {
    @Query(value = """
            SELECT e
            FROM Parent e
            ORDER BY e.id
            LIMIT :limit
            OFFSET :offset""")
    List<Parent> selectParentWithPagination(@Param("limit") long limit,
                                         @Param("offset") long offset);
    boolean existsByPhoneNumber(PhoneNumber phoneNumber);
}
package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.Bus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long> {
    boolean existsBySchool_IdAndBusNumberIgnoreCase(Integer id, String busNumber);

    Page<Bus> findAllBySchoolId(Integer schoolId, Pageable pageable);
}
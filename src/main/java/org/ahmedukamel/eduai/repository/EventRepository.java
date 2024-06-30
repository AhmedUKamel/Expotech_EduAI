package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.Event;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    Page<Event> findAllBySchoolIdOrderByEventStartTimeDesc(Integer schoolId, Pageable pageable);
}
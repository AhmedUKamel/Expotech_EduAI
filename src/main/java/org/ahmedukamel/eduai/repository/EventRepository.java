package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
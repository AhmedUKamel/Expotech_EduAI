package org.ahmedukamel.eduai.repository;

import jakarta.transaction.Transactional;
import org.ahmedukamel.eduai.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    Page<Event> findAllBySchoolIdOrderByEventStartTimeDesc(Integer schoolId, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "Delete from event_attendees where event_id = :eventId and attendee_id = :attendeeId", nativeQuery = true)
    void deleteAttendeeForEvent(Long eventId, Long attendeeId);
}
package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.ScheduleItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleItemRepository extends JpaRepository<ScheduleItem, Long> {

    Page<ScheduleItem> findAllByTeacherIdAndDeleted(Long teacherId, boolean deleted, Pageable pageable);

    Page<ScheduleItem> findAllByClassroomIdAndDeleted(Long classroomId, boolean deleted, Pageable pageable);
}

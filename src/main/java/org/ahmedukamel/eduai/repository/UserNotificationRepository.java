package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.UserNotification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserNotificationRepository extends JpaRepository<UserNotification, UserNotification.UserNotificationId> {
    Page<UserNotification> findAllByUser_Id(Long userId, Pageable pageable);
}
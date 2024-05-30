package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.Attachment;
import org.ahmedukamel.eduai.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
    Page<Attachment> findAllByUser(User user, Pageable pageable);
}
package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.PostComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComment, Long> {

    Page<PostComment> findAllByPostIdAndDeletedOrderByUpdatedDate(Long postId, boolean deleted, Pageable pageable);
}
package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.Post;
import org.ahmedukamel.eduai.model.PostComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllBySchoolIdAndDeletedOrderByUpdatedDate(Integer schoolId, boolean deleted, Pageable pageable);
}
package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.StudentActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentActivityRepository extends JpaRepository<StudentActivity, Long> {
}
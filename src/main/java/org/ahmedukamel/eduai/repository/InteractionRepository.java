package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.Interaction;
import org.ahmedukamel.eduai.model.Parent;
import org.ahmedukamel.eduai.model.Student;
import org.ahmedukamel.eduai.model.Teacher;
import org.ahmedukamel.eduai.model.enumeration.InteractionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InteractionRepository extends JpaRepository<Interaction, Long> {
    Page<Interaction> findAllByStudentAndType(Student student, InteractionType type, Pageable pageable);

    Page<Interaction> findAllByTeacherAndType(Teacher teacher, InteractionType type, Pageable pageable);

    Page<Interaction> findAllByTeacherAndTypeOrType(Teacher teacher, InteractionType type1, InteractionType type2, Pageable pageable);

    Page<Interaction> findAllByParentAndType(Parent parent, InteractionType type, Pageable pageable);
}
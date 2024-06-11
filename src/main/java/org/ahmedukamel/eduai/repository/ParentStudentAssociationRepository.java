package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.Parent;
import org.ahmedukamel.eduai.model.ParentStudentAssociation;
import org.ahmedukamel.eduai.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentStudentAssociationRepository
        extends JpaRepository<ParentStudentAssociation, ParentStudentAssociation.ParentStudentAssociationId> {

    boolean existsByParent_IdAndStudent_Id(Long parentId, Long studentId);

    Page<ParentStudentAssociation> findAllByParent(Parent parent, Pageable pageable);

    Page<ParentStudentAssociation> findAllByStudent(Student student, Pageable pageable);
}
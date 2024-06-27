package org.ahmedukamel.eduai.service.parent_student_association;

public interface IParentStudentAssociationManagementService {
    Object createParentStudentAssociation(Object object);

    Object deleteParentStudentAssociation(Object object);

    Object getParentStudentAssociation(Object object);

    Object getParentStudentAssociationsByParent(Long parentId, int pageSize, int pageNumber);

    Object getParentStudentAssociationsByStudent(Long studentId, int pageSize, int pageNumber);
}
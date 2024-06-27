package org.ahmedukamel.eduai.saver.parent_student_association;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.parent_student_association.CreateParentStudentAssociationRequest;
import org.ahmedukamel.eduai.model.Parent;
import org.ahmedukamel.eduai.model.ParentStudentAssociation;
import org.ahmedukamel.eduai.model.Student;
import org.ahmedukamel.eduai.repository.ParentRepository;
import org.ahmedukamel.eduai.repository.ParentStudentAssociationRepository;
import org.ahmedukamel.eduai.repository.StudentRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ParentStudentAssociationSaver
        implements Function<CreateParentStudentAssociationRequest, ParentStudentAssociation> {

    private final ParentStudentAssociationRepository associationRepository;
    private final StudentRepository studentRepository;
    private final ParentRepository parentRepository;

    @Override
    public ParentStudentAssociation apply(CreateParentStudentAssociationRequest request) {
        DatabaseService.unique(associationRepository::existsByParent_IdAndStudent_Id,
                request.parentId(), request.studentId(), ParentStudentAssociation.class);

        Parent parent = DatabaseService.get(parentRepository::findById, request.parentId(), Parent.class);
        Student student = DatabaseService.get(studentRepository::findById, request.studentId(), Student.class);

        ParentStudentAssociation association = ParentStudentAssociation
                .builder()
                .parent(parent)
                .student(student)
                .type(request.type())
                .build();

        return associationRepository.save(association);
    }
}
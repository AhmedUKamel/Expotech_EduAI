package org.ahmedukamel.eduai.service.parent_student_association;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.parent_student_association.CreateParentStudentAssociationRequest;
import org.ahmedukamel.eduai.dto.parent_student_association.ParentStudentAssociationIdRequest;
import org.ahmedukamel.eduai.dto.parent_student_association.ParentStudentAssociationResponse;
import org.ahmedukamel.eduai.mapper.parent_student_association.ParentStudentAssociationResponseMapper;
import org.ahmedukamel.eduai.model.Parent;
import org.ahmedukamel.eduai.model.ParentStudentAssociation;
import org.ahmedukamel.eduai.model.ParentStudentAssociation.ParentStudentAssociationId;
import org.ahmedukamel.eduai.model.Student;
import org.ahmedukamel.eduai.repository.ParentRepository;
import org.ahmedukamel.eduai.repository.ParentStudentAssociationRepository;
import org.ahmedukamel.eduai.repository.StudentRepository;
import org.ahmedukamel.eduai.saver.parent_student_association.ParentStudentAssociationSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParentStudentAssociationManagementService
        implements IParentStudentAssociationManagementService {

    private final ParentStudentAssociationResponseMapper associationResponseMapper;
    private final ParentStudentAssociationRepository associationRepository;
    private final ParentStudentAssociationSaver associationSaver;
    private final ParentRepository parentRepository;
    private final StudentRepository studentRepository;

    @Override
    public Object createParentStudentAssociation(Object object) {
        CreateParentStudentAssociationRequest request = (CreateParentStudentAssociationRequest) object;

        ParentStudentAssociation association = associationSaver.apply(request);

        ParentStudentAssociationResponse response = associationResponseMapper.apply(association);
        String message = "Parent and student association created successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object deleteParentStudentAssociation(Object object) {
        ParentStudentAssociationIdRequest request = (ParentStudentAssociationIdRequest) object;

        Parent parent = DatabaseService.get(parentRepository::findById, request.parentId(), Parent.class);
        Student student = DatabaseService.get(studentRepository::findById, request.studentId(), Student.class);

        ParentStudentAssociationId id = new ParentStudentAssociationId(parent, student);
        ParentStudentAssociation association = DatabaseService.get(associationRepository::findById, id, ParentStudentAssociation.class);

        associationRepository.delete(association);

        String message = "Parent and student association deleted successfully.";

        return new ApiResponse(true, message, "");
    }

    @Override
    public Object getParentStudentAssociation(Object object) {
        ParentStudentAssociationIdRequest request = (ParentStudentAssociationIdRequest) object;

        Parent parent = DatabaseService.get(parentRepository::findById, request.parentId(), Parent.class);
        Student student = DatabaseService.get(studentRepository::findById, request.studentId(), Student.class);

        ParentStudentAssociationId id = new ParentStudentAssociationId(parent, student);
        ParentStudentAssociation association = DatabaseService.get(associationRepository::findById, id, ParentStudentAssociation.class);

        ParentStudentAssociationResponse response = associationResponseMapper.apply(association);
        String message = "Parent and student association retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getParentStudentAssociationsByParent(Long parentId, int pageSize, int pageNumber) {
        Parent parent = DatabaseService.get(parentRepository::findById, parentId, Parent.class);
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by("createdAt").descending());

        Page<ParentStudentAssociation> associations = associationRepository.findAllByParent(parent, pageable);

        Page<ParentStudentAssociationResponse> response = associations.map(associationResponseMapper);
        String message = "Parent and student associations for parent retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getParentStudentAssociationsByStudent(Long studentId, int pageSize, int pageNumber) {
        Student student = DatabaseService.get(studentRepository::findById, studentId, Student.class);
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by("createdAt").descending());

        Page<ParentStudentAssociation> associations = associationRepository.findAllByStudent(student, pageable);

        Page<ParentStudentAssociationResponse> response = associations.map(associationResponseMapper);
        String message = "Parent and student associations for student retrieved successfully.";

        return new ApiResponse(true, message, response);
    }
}
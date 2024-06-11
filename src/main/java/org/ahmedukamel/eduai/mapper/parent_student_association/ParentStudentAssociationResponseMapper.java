package org.ahmedukamel.eduai.mapper.parent_student_association;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.parent_student_association.ParentStudentAssociationResponse;
import org.ahmedukamel.eduai.model.ParentStudentAssociation;
import org.ahmedukamel.eduai.service.message.MessageSourceService;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ParentStudentAssociationResponseMapper
        implements Function<ParentStudentAssociation, ParentStudentAssociationResponse> {

    private final MessageSourceService messageSourceService;

    @Override
    public ParentStudentAssociationResponse apply(ParentStudentAssociation association) {
        return new ParentStudentAssociationResponse(
                association.getParent().getId(),
                association.getStudent().getId(),
                messageSourceService.getAssociationType(association.getType()),
                association.getCreatedAt()
        );
    }
}
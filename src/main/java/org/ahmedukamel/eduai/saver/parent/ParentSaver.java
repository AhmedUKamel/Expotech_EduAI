package org.ahmedukamel.eduai.saver.parent;

import org.ahmedukamel.eduai.dto.parent.CreateParentRequest;
import org.ahmedukamel.eduai.model.Parent;
import org.ahmedukamel.eduai.repository.ParentRepository;

import java.util.function.Function;

public class ParentSaver implements Function<CreateParentRequest, Parent> {
    private final ParentRepository parentRepository;

    public ParentSaver(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    @Override
    public Parent apply(CreateParentRequest createParentRequest) {
        Parent parent = Parent.builder()
                .fullName(createParentRequest.fullName())
                .age(createParentRequest.age())
                .gender(createParentRequest.gender())
                .numberOfChildren(createParentRequest.numberOfChildren())
                .occupation(createParentRequest.occupation())
                .religion(createParentRequest.religion())
                .dateOfBirth(createParentRequest.dateOfBirth())
                .address(createParentRequest.address())
                .email(createParentRequest.email())
                .build();
        return parentRepository.save(parent);
    }

}


package org.ahmedukamel.eduai.updater.parent;

import org.ahmedukamel.eduai.dto.parent.UpdateParentRequest;
import org.ahmedukamel.eduai.model.Parent;
import org.ahmedukamel.eduai.repository.ParentRepository;

import java.util.function.BiFunction;

public class ParentUpdater implements BiFunction<Parent, UpdateParentRequest,Parent> {
    private final ParentRepository parentRepository;

    public ParentUpdater(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    @Override
    public Parent apply(Parent parent, UpdateParentRequest request) {
        parent.setFullName(request.fullName());
        parent.setAge(request.age());
        parent.setGender(request.gender());
        parent.setNumberOfChildren(request.numberOfChildren());
        parent.setOccupation(request.occupation());
        parent.setReligion(request.religion());
        parent.setDateOfBirth(request.dateOfBirth());
        parent.setAddress(request.address());
        parent.setEmail(request.email());

        parentRepository.save(parent);

        return parent;
    }
}

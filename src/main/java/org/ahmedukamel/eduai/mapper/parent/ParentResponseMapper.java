package org.ahmedukamel.eduai.mapper.parent;

import org.ahmedukamel.eduai.dto.parent.ParentResponse;
import org.ahmedukamel.eduai.model.Parent;

import java.util.function.Function;

public class ParentResponseMapper implements Function<Parent, ParentResponse> {
    @Override
    public ParentResponse apply(Parent parent) {
        return new ParentResponse(parent.getId(),
                parent.getFullName(),
                parent.getAge(),
                parent.getGender(),
                parent.getNumberOfChildren(),
                parent.getOccupation(),
                parent.getReligion(),
                parent.getDateOfBirth(),
                parent.getAddress(),
                parent.getEmail());
    }
}

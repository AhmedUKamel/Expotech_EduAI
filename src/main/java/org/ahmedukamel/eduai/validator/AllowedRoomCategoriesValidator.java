package org.ahmedukamel.eduai.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.annotation.AllowedRoomCategories;
import org.ahmedukamel.eduai.model.enumeration.RoomCategory;

import java.util.List;

@RequiredArgsConstructor
public class AllowedRoomCategoriesValidator implements ConstraintValidator<AllowedRoomCategories, RoomCategory> {
    private static final List<RoomCategory> RESTRICTED_ROOM_CATEGORIES = List.of(
            RoomCategory.CLASSROOM, RoomCategory.LAB, RoomCategory.OFFICE
    );

    @Override
    public boolean isValid(RoomCategory roomCategory, ConstraintValidatorContext constraintValidatorContext) {
        return !RESTRICTED_ROOM_CATEGORIES.contains(roomCategory);
    }
}
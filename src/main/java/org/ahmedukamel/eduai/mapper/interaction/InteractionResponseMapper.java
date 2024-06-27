package org.ahmedukamel.eduai.mapper.interaction;

import org.ahmedukamel.eduai.dto.interaction.InteractionResponse;
import org.ahmedukamel.eduai.model.Interaction;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.function.Function;

@Component
public class InteractionResponseMapper implements Function<Interaction, InteractionResponse> {
    @Override
    public InteractionResponse apply(Interaction interaction) {
        Long parentId = Objects.nonNull(interaction.getParent()) ? interaction.getParent().getId() : null,
                studentId = Objects.nonNull(interaction.getStudent()) ? interaction.getStudent().getId() : null,
                teacherId = Objects.nonNull(interaction.getTeacher()) ? interaction.getTeacher().getId() : null;

        return new InteractionResponse(
                interaction.getId(),
                interaction.getDescription(),
                interaction.getCreationDate(),
                interaction.getType().name(),
                parentId,
                studentId,
                teacherId
        );
    }
}
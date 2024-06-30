package org.ahmedukamel.eduai.saver.interaction;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.interaction.CreateInteractionRequest;
import org.ahmedukamel.eduai.model.*;
import org.ahmedukamel.eduai.model.enumeration.InteractionType;
import org.ahmedukamel.eduai.model.enumeration.Role;
import org.ahmedukamel.eduai.repository.InteractionRepository;
import org.ahmedukamel.eduai.repository.UserRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.util.context.ContextHolderUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class InteractionSaver implements Function<CreateInteractionRequest, Interaction> {
    private final InteractionRepository interactionRepository;
    private final UserRepository userRepository;

    @Override
    public Interaction apply(CreateInteractionRequest request) {
        User fromUser = ContextHolderUtils.getUser(),
                toUser = DatabaseService.get(userRepository::findById, request.userId(), User.class);

        InteractionType type = Arrays.stream(InteractionType.values())
                .filter(interactionType ->
                        interactionType.getTo().equals(toUser.getRole()) &&
                        interactionType.getFrom().equals(fromUser.getRole()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No valid interaction type found."));

        Student student = toUser.getRole().equals(Role.STUDENT) ? toUser.getStudent() :
                fromUser.getRole().equals(Role.STUDENT) ? fromUser.getStudent() : null;

        Teacher teacher = toUser.getRole().equals(Role.TEACHER) ? (Teacher) toUser :
                fromUser.getRole().equals(Role.TEACHER) ? (Teacher) fromUser : null;

        Parent parent = toUser.getRole().equals(Role.PARENT) ? toUser.getParent() :
                fromUser.getRole().equals(Role.PARENT) ? fromUser.getParent() : null;

        Interaction interaction = Interaction
                .builder()
                .type(type)
                .student(student)
                .teacher(teacher)
                .parent(parent)
                .description(request.description().strip())
                .build();

        return interactionRepository.save(interaction);
    }
}
package org.ahmedukamel.eduai.service.interaction;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.interaction.CreateInteractionRequest;
import org.ahmedukamel.eduai.dto.interaction.InteractionResponse;
import org.ahmedukamel.eduai.mapper.interaction.InteractionResponseMapper;
import org.ahmedukamel.eduai.model.Interaction;
import org.ahmedukamel.eduai.model.Teacher;
import org.ahmedukamel.eduai.model.User;
import org.ahmedukamel.eduai.model.enumeration.InteractionType;
import org.ahmedukamel.eduai.repository.InteractionRepository;
import org.ahmedukamel.eduai.saver.interaction.InteractionSaver;
import org.ahmedukamel.eduai.util.context.ContextHolderUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InteractionService implements IInteractionService {
    private final InteractionResponseMapper interactionResponseMapper;
    private final InteractionRepository interactionRepository;
    private final InteractionSaver interactionSaver;

    @Override
    public Object createInteraction(Object object) {
        CreateInteractionRequest request = (CreateInteractionRequest) object;

        Interaction savedInteraction = interactionSaver.apply(request);

        InteractionResponse response = interactionResponseMapper.apply(savedInteraction);
        String message = "Interaction created successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getMyInteractions(int pageSize, int pageNumber) {
        User user = ContextHolderUtils.getUser();

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        final Page<Interaction> interactions;

        switch (user.getRole()) {
            case PARENT -> interactions = interactionRepository.findAllByParentAndType(
                    user.getParent(), InteractionType.PARENT_TO_TEACHER, pageable);

            case STUDENT -> interactions = interactionRepository.findAllByStudentAndType(
                    user.getStudent(), InteractionType.STUDENT_TO_TEACHER, pageable);

            case TEACHER -> interactions = interactionRepository.findAllByTeacherAndType(
                    (Teacher) user, InteractionType.TEACHER_TO_STUDENT, pageable);

            default -> throw new IllegalStateException("Unexpected value: " + user.getRole());
        }

        Page<InteractionResponse> response = interactions.map(interactionResponseMapper);
        String message = "Interactions retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getInteractionsToMe(int pageSize, int pageNumber) {
        User user = ContextHolderUtils.getUser();

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        final Page<Interaction> interactions;

        switch (user.getRole()) {
            case STUDENT -> interactions = interactionRepository.findAllByStudentAndType(
                    user.getStudent(), InteractionType.TEACHER_TO_STUDENT, pageable);

            case TEACHER -> interactions = interactionRepository.findAllByTeacherAndTypeOrType(
                    (Teacher) user, InteractionType.STUDENT_TO_TEACHER, InteractionType.PARENT_TO_TEACHER, pageable);

            default -> throw new IllegalStateException("Unexpected value: " + user.getRole());
        }

        Page<InteractionResponse> response = interactions.map(interactionResponseMapper);
        String message = "Interactions retrieved successfully.";

        return new ApiResponse(true, message, response);
    }
}
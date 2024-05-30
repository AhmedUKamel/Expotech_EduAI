package org.ahmedukamel.eduai.service.interaction;

public interface IInteractionService {
    Object createInteraction(Object object);

    Object getMyInteractions(int pageSize, int pageNumber);

    Object getInteractionsToMe(int pageSize, int pageNumber);
}
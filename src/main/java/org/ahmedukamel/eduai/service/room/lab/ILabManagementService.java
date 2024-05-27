package org.ahmedukamel.eduai.service.room.lab;

public interface ILabManagementService {
    Object createLab(Object object);

    Object updateLab(Long id, Object object);

    Object deleteLab(Long id);

    Object getLab(Long id);

    Object getAllLabs(long pageSize, long pageNumber);
}
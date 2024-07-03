package org.ahmedukamel.eduai.service.position;

public interface IPositionManagementService {
    Object createPosition(Object object);

    Object updatePosition(Integer id, Object object);

    Object deletePosition(Integer id);

    Object getPosition(Integer id);

    Object getAllPositions(int pageSize, int pageNumber);
}
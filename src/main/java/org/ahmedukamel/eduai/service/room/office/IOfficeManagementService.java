package org.ahmedukamel.eduai.service.room.office;

public interface IOfficeManagementService {
    Object createOffice(Object object);

    Object updateOffice(Long id, Object object);

    Object deleteOffice(Long id);

    Object getOffice(Long id);

    Object getAllOffices(long pageSize, long pageNumber);
}
package org.ahmedukamel.eduai.service.bus;

public interface IBusManagementService {
    Object createBus(Object object);

    Object updateBus(Long id, Object object);

    Object deleteBus(Long id);

    Object getBus(Long id);

    Object getAllBuses(boolean getActive, int pageSize, int pageNumber);
}
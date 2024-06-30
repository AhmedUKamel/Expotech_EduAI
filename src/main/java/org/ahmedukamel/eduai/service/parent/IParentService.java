package org.ahmedukamel.eduai.service.parent;

public interface IParentService {
    Object createParent(Object object);

    Object updateParent(Long id, Object object);

    Object deleteParent(Long id);

    Object getParent(Long id);

    Object getAllParent(long pageSize, long pageNumber);
}

package org.ahmedukamel.eduai.service.parent;

public interface IParentService {
    Object addParent(Object object);

    Object deleteParent(Long id);

    Object getParent(Long id);

    Object getAllParents(int pageSize, int pageNumber);
}

package org.ahmedukamel.eduai.service.parent;

public interface IParentManagementService {
    Object addParent(Object object);

    Object updateParent(Object object);

    Object setParentAccountLock(Long id, boolean accountLocked);

    Object getParent(Long id);

    Object getAllParents(int pageSize, int pageNumber);
}
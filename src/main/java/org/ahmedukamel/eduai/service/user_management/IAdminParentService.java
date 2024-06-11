package org.ahmedukamel.eduai.service.user_management;

public interface IAdminParentService {
    Object getAllParents(int pageSize, int pageNumber);

    Object getParent(Long parentId);
}
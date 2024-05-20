package org.ahmedukamel.eduai.service.classes;

public interface IClassManagementService {
    Object createClass(Object object);

    Object updateClass(Integer id, Object object);

    Object deleteClass(Integer id);

    Object getClass(Integer id);

    Object getAllClasses(long pageSize, long pageNumber);
}
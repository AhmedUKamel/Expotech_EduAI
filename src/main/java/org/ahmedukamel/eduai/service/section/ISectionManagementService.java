package org.ahmedukamel.eduai.service.section;

public interface ISectionManagementService {
    Object createSection(Object object);

    Object updateSection(Integer id, Object object);

    Object deleteSection(Integer id);

    Object getSection(Integer id);

    Object getAllSections(long pageSize, long pageNumber);
}
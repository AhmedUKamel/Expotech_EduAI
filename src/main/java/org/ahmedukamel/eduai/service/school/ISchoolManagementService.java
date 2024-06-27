package org.ahmedukamel.eduai.service.school;

public interface ISchoolManagementService {
    Object createSchool(Object object);

    Object updateSchool(Integer id, Object object);

    Object deleteSchool(Integer id);

    Object getSchool(Integer id);

    Object getAllSchools(long pageSize, long pageNumber);
}
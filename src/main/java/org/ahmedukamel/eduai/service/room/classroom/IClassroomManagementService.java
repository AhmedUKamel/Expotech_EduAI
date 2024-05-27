package org.ahmedukamel.eduai.service.room.classroom;

public interface IClassroomManagementService {
    Object createClassroom(Object object);

    Object updateClassroom(Long id, Object object);

    Object deleteClassroom(Long id);

    Object getClassroom(Long id);

    Object getAllClassrooms(long pageSize, long pageNumber);
}
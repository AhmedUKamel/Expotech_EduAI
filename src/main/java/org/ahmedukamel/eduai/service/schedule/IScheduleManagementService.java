package org.ahmedukamel.eduai.service.schedule;

public interface IScheduleManagementService {

    Object createScheduleItem(Object object);

    Object updateScheduleItem(Long scheduleItemId, Object object);

    Object getScheduleItem(Long scheduleItemId);

    Object deleteScheduleItem(Long scheduleItemId);

    Object getScheduleForTeacher(Long teacherId, boolean getActive, int pageSize, int pageNumber);

    Object getScheduleForClassroom(Long classroomId, boolean getActive, int pageSize, int pageNumber);
}

package org.ahmedukamel.eduai.service.course;

public interface ICourseManagementService {
    Object addCourse(Object object);

    Object updateCourse(Long id, Object object);

    Object deleteCourse(Long id);

    Object getCourse(Long id);

    Object getAllCourses(int pageNumber, int pageSize);
}
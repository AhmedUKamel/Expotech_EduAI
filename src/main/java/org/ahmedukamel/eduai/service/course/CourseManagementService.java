package org.ahmedukamel.eduai.service.course;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.course.AddCourseRequest;
import org.ahmedukamel.eduai.dto.course.AllLanguagesCourseResponse;
import org.ahmedukamel.eduai.dto.course.SingleLanguageCourseResponse;
import org.ahmedukamel.eduai.dto.course.UpdateCourseRequest;
import org.ahmedukamel.eduai.mapper.course.AllLanguagesCourseResponseMapper;
import org.ahmedukamel.eduai.mapper.course.SingleLanguageCourseResponseMapper;
import org.ahmedukamel.eduai.model.Course;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.repository.CourseRepository;
import org.ahmedukamel.eduai.saver.course.AddCourseRequestSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.updater.course.UpdateCourseRequestUpdater;
import org.ahmedukamel.eduai.util.context.ContextHolderUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseManagementService implements ICourseManagementService {
    private final SingleLanguageCourseResponseMapper singleLanguageCourseResponseMapper;
    private final AllLanguagesCourseResponseMapper allLanguagesCourseResponseMapper;
    private final UpdateCourseRequestUpdater updateCourseRequestUpdater;
    private final AddCourseRequestSaver addCourseRequestSaver;
    private final CourseRepository courseRepository;

    @Override
    public Object addCourse(Object object) {
        AddCourseRequest request = (AddCourseRequest) object;
        School school = ContextHolderUtils.getSchool();

        Course savedCourse = addCourseRequestSaver.apply(request, school);

        AllLanguagesCourseResponse response = allLanguagesCourseResponseMapper.apply(savedCourse);
        String message = "Course added successfully";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object updateCourse(Long id, Object object) {
        UpdateCourseRequest request = (UpdateCourseRequest) object;
        School school = ContextHolderUtils.getSchool();
        Course course = DatabaseService.get(courseRepository::findByIdAndSchool_Id,
                id, school.getId(), Course.class);

        Course updatedCourse = updateCourseRequestUpdater.apply(course, request);

        AllLanguagesCourseResponse response = allLanguagesCourseResponseMapper.apply(updatedCourse);
        String message = "Course updated successfully";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object deleteCourse(Long id) {
        School school = ContextHolderUtils.getSchool();
        Course course = DatabaseService.get(courseRepository::findByIdAndSchool_Id,
                id, school.getId(), Course.class);

        try {
            courseRepository.delete(course);
        } catch (DataIntegrityViolationException exception) {
            throw new RuntimeException("This course is associated with other records and can't be deleted.", exception);
        }

        String message = "Course deleted successfully";

        return new ApiResponse(true, message, "");
    }

    @Override
    public Object getCourse(Long id) {
        School school = ContextHolderUtils.getSchool();
        Course course = DatabaseService.get(courseRepository::findByIdAndSchool_Id,
                id, school.getId(), Course.class);

        AllLanguagesCourseResponse response = allLanguagesCourseResponseMapper.apply(course);
        String message = "Course retrieved successfully";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getAllCourses(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        School school = ContextHolderUtils.getSchool();

        Page<Course> courses = courseRepository.findAllBySchool_Id(school.getId(), pageable);

        Page<SingleLanguageCourseResponse> response = courses.map(singleLanguageCourseResponseMapper);
        String message = "All courses retrieved successfully";

        return new ApiResponse(true, message, response);
    }
}
package org.ahmedukamel.eduai.updater.course;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.course.UpdateCourseRequest;
import org.ahmedukamel.eduai.model.Course;
import org.ahmedukamel.eduai.model.CourseDetails;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.repository.CourseRepository;
import org.ahmedukamel.eduai.util.language.LanguageUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class UpdateCourseRequestUpdater
        implements BiFunction<Course, UpdateCourseRequest, Course> {

    private final CourseRepository courseRepository;

    @Override
    public Course apply(Course course, UpdateCourseRequest request) {
        CourseDetails courseDetails_en = LanguageUtils.getDetails(course.getCourseDetails(), Language.ENGLISH);
        CourseDetails courseDetails_ar = LanguageUtils.getDetails(course.getCourseDetails(), Language.ARABIC);
        CourseDetails courseDetails_fr = LanguageUtils.getDetails(course.getCourseDetails(), Language.FRENCH);

        courseDetails_en.setName(request.name_en().strip());
        courseDetails_ar.setName(request.name_ar().strip());
        courseDetails_fr.setName(request.name_fr().strip());

        courseDetails_en.setDescription(request.description_en().strip());
        courseDetails_ar.setDescription(request.description_ar().strip());
        courseDetails_fr.setDescription(request.description_fr().strip());

        Set<Course> prerequisites = request.prerequisiteIds()
                .stream()
                .flatMap(Stream::ofNullable)
                .map(courseRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());

        course.setPrerequisites(prerequisites);

        return courseRepository.save(course);
    }
}
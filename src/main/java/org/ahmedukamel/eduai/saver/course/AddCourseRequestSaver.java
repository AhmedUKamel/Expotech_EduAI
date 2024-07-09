package org.ahmedukamel.eduai.saver.course;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.course.AddCourseRequest;
import org.ahmedukamel.eduai.model.Course;
import org.ahmedukamel.eduai.model.CourseDetails;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.repository.CourseRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class AddCourseRequestSaver
        implements BiFunction<AddCourseRequest, School, Course> {

    private final CourseRepository courseRepository;

    @Override
    public Course apply(AddCourseRequest request, School school) {
        DatabaseService.unique(courseRepository::existsBySchool_IdAndCodeIgnoreCase,
                school.getId(), request.code().strip(), Course.class);

        Set<Course> prerequisite = request.prerequisiteIds()
                .stream()
                .flatMap(Stream::ofNullable)
                .map(courseRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());

        String code = request.code().strip().toUpperCase();

        Course course = new Course();

        CourseDetails courseDetails_en = new CourseDetails(
                course, Language.ENGLISH, request.name_en(), request.description_en());

        CourseDetails courseDetails_ar = new CourseDetails(
                course, Language.ARABIC, request.name_ar(), request.description_ar()
        );

        CourseDetails courseDetails_fr = new CourseDetails(
                course, Language.FRENCH, request.name_fr(), request.description_fr()
        );

        course.setCode(code);
        course.setSchool(school);
        course.setLevel(request.level());
        course.setPrerequisites(prerequisite);
        course.setCourseDetails(Set.of(courseDetails_en, courseDetails_ar, courseDetails_fr));

        return courseRepository.save(course);
    }
}
package org.ahmedukamel.eduai.mapper.course;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.course.AllLanguagesCourseResponse;
import org.ahmedukamel.eduai.model.Course;
import org.ahmedukamel.eduai.model.CourseDetails;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.service.message.MessageSourceService;
import org.ahmedukamel.eduai.util.language.LanguageUtils;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AllLanguagesCourseResponseMapper
        implements Function<Course, AllLanguagesCourseResponse> {

    private final MessageSourceService messageSourceService;

    @Override
    public AllLanguagesCourseResponse apply(Course course) {
        CourseDetails courseDetails_en = LanguageUtils.getDetails(course.getCourseDetails(), Language.ENGLISH);
        CourseDetails courseDetails_ar = LanguageUtils.getDetails(course.getCourseDetails(), Language.ARABIC);
        CourseDetails courseDetails_fr = LanguageUtils.getDetails(course.getCourseDetails(), Language.FRENCH);
        Set<Long> prerequisiteIds = course.getPrerequisites().stream().map(Course::getId).collect(Collectors.toSet());

        return new AllLanguagesCourseResponse(
                course.getId(),
                course.getCode(),
                messageSourceService.getStudyLevel(course.getLevel()),
                courseDetails_en.getName(),
                courseDetails_ar.getName(),
                courseDetails_en.getName(),
                courseDetails_en.getDescription(),
                courseDetails_ar.getDescription(),
                courseDetails_fr.getDescription(),
                course.getCreationDate(),
                course.getUpdatedDate(),
                prerequisiteIds
        );
    }
}
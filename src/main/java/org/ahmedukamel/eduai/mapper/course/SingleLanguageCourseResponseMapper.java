package org.ahmedukamel.eduai.mapper.course;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.course.SingleLanguageCourseResponse;
import org.ahmedukamel.eduai.model.Course;
import org.ahmedukamel.eduai.model.CourseDetails;
import org.ahmedukamel.eduai.service.message.MessageSourceService;
import org.ahmedukamel.eduai.util.language.LanguageUtils;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SingleLanguageCourseResponseMapper
        implements Function<Course, SingleLanguageCourseResponse> {

    private final MessageSourceService messageSourceService;

    @Override
    public SingleLanguageCourseResponse apply(Course course) {
        CourseDetails courseDetails = LanguageUtils.getDetails(course.getCourseDetails());
        Set<Long> prerequisiteIds = course.getPrerequisites().stream().map(Course::getId).collect(Collectors.toSet());

        return new SingleLanguageCourseResponse(
                course.getId(),
                course.getCode(),
                messageSourceService.getStudyLevel(course.getLevel()),
                courseDetails.getName(),
                courseDetails.getDescription(),
                course.getCreationDate(),
                course.getUpdatedDate(),
                prerequisiteIds
        );
    }
}
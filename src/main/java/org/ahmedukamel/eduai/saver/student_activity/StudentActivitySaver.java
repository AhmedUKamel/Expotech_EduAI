package org.ahmedukamel.eduai.saver.student_activity;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.student_activity.CreateStudentActivityRequest;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.model.StudentActivity;
import org.ahmedukamel.eduai.model.StudentActivityDetail;
import org.ahmedukamel.eduai.model.embeddable.Location;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.repository.SchoolRepository;
import org.ahmedukamel.eduai.repository.StudentActivityRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class StudentActivitySaver implements Function<CreateStudentActivityRequest, StudentActivity> {
    private final StudentActivityRepository studentActivityRepository;
    private final SchoolRepository schoolRepository;

    @Override
    public StudentActivity apply(CreateStudentActivityRequest request) {
        School school = DatabaseService.get(schoolRepository::findById, request.schoolId(), School.class);

        Location location = new Location(request.latitude(), request.longitude());

        StudentActivity studentActivity = StudentActivity
                .builder()
                .school(school)
                .dateTime(request.dateTime())
                .location(location)
                .build();

        StudentActivityDetail studentActivityDetail_en = StudentActivityDetail
                .builder()
                .studentActivity(studentActivity)
                .language(Language.ENGLISH)
                .name(request.name_en().strip())
                .description(request.description_en().strip())
                .location(request.location_en().strip())
                .build(),

                studentActivityDetail_ar = StudentActivityDetail
                        .builder()
                        .studentActivity(studentActivity)
                        .language(Language.ARABIC)
                        .name(request.name_ar().strip())
                        .description(request.description_ar().strip())
                        .location(request.location_ar().strip())
                        .build(),

                studentActivityDetail_fr = StudentActivityDetail
                        .builder()
                        .studentActivity(studentActivity)
                        .language(Language.FRENCH)
                        .name(request.name_fr().strip())
                        .description(request.description_fr().strip())
                        .location(request.location_fr().strip())
                        .build();

        studentActivity.setDetails(Set.of(studentActivityDetail_en, studentActivityDetail_ar, studentActivityDetail_fr));

        return studentActivityRepository.save(studentActivity);
    }
}
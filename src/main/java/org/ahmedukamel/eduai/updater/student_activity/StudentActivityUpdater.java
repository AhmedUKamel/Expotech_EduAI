package org.ahmedukamel.eduai.updater.student_activity;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.student_activity.UpdateStudentActivityRequest;
import org.ahmedukamel.eduai.model.StudentActivity;
import org.ahmedukamel.eduai.model.StudentActivityDetail;
import org.ahmedukamel.eduai.model.embeddable.Location;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.repository.StudentActivityRepository;
import org.ahmedukamel.eduai.util.student_activity.StudentActivityUtils;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class StudentActivityUpdater implements BiFunction<StudentActivity, UpdateStudentActivityRequest, StudentActivity> {
    private final StudentActivityRepository studentActivityRepository;

    @Override
    public StudentActivity apply(StudentActivity studentActivity, UpdateStudentActivityRequest request) {
        StudentActivityDetail studentActivityDetail_en = StudentActivityUtils
                .getStudentActivityDetail(studentActivity, Language.ENGLISH),

                studentActivityDetail_ar = StudentActivityUtils
                        .getStudentActivityDetail(studentActivity, Language.ARABIC),

                studentActivityDetail_fr = StudentActivityUtils
                        .getStudentActivityDetail(studentActivity, Language.FRENCH);

        studentActivityDetail_en.setName(request.name_en().strip());
        studentActivityDetail_en.setDescription(request.description_en().strip());
        studentActivityDetail_en.setLocation(request.location_en().strip());

        studentActivityDetail_ar.setName(request.name_ar().strip());
        studentActivityDetail_ar.setDescription(request.description_ar().strip());
        studentActivityDetail_ar.setLocation(request.location_ar().strip());

        studentActivityDetail_fr.setName(request.name_fr().strip());
        studentActivityDetail_fr.setDescription(request.description_fr().strip());
        studentActivityDetail_fr.setLocation(request.location_fr().strip());

        Location location = new Location(request.latitude(), request.longitude());

        studentActivity.setLocation(location);
        studentActivity.setDateTime(request.dateTime());

        return studentActivityRepository.save(studentActivity);
    }
}
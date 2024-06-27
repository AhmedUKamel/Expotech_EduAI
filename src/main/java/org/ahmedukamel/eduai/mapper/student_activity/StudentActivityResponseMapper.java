package org.ahmedukamel.eduai.mapper.student_activity;

import org.ahmedukamel.eduai.dto.student_activity.StudentActivityResponse;
import org.ahmedukamel.eduai.model.StudentActivity;
import org.ahmedukamel.eduai.model.StudentActivityDetail;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.util.student_activity.StudentActivityUtils;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class StudentActivityResponseMapper implements Function<StudentActivity, StudentActivityResponse> {
    @Override
    public StudentActivityResponse apply(StudentActivity studentActivity) {
        StudentActivityDetail studentActivityDetail_en = StudentActivityUtils.getStudentActivityDetail(
                studentActivity, Language.ENGLISH),

                studentActivityDetail_ar = StudentActivityUtils.getStudentActivityDetail(
                        studentActivity, Language.ARABIC),

                studentActivityDetail_fr = StudentActivityUtils.getStudentActivityDetail(
                        studentActivity, Language.FRENCH);

        return new StudentActivityResponse(
                studentActivity.getId(),
                studentActivity.getSchool().getId(),
                studentActivity.getDateTime(),
                studentActivity.getLocation().getLatitude(),
                studentActivity.getLocation().getLongitude(),
                studentActivityDetail_en.getName(),
                studentActivityDetail_en.getDescription(),
                studentActivityDetail_en.getLocation(),
                studentActivityDetail_ar.getName(),
                studentActivityDetail_ar.getDescription(),
                studentActivityDetail_ar.getLocation(),
                studentActivityDetail_fr.getName(),
                studentActivityDetail_fr.getDescription(),
                studentActivityDetail_fr.getLocation()
        );
    }
}
package org.ahmedukamel.eduai.util.student_activity;

import org.ahmedukamel.eduai.model.StudentActivity;
import org.ahmedukamel.eduai.model.StudentActivityDetail;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.springframework.context.i18n.LocaleContextHolder;

public class StudentActivityUtils {
    public static StudentActivityDetail getStudentActivityDetail(StudentActivity studentActivity) {
        return getStudentActivityDetail(studentActivity, LocaleContextHolder.getLocale().getLanguage());
    }

    public static StudentActivityDetail getStudentActivityDetail(StudentActivity studentActivity, Language language) {
        return getStudentActivityDetail(studentActivity, language.getCode());
    }

    public static StudentActivityDetail getStudentActivityDetail(StudentActivity studentActivity, String languageCode) {
        return studentActivity.getDetails()
                .stream()
                .filter(studentActivityDetail -> studentActivityDetail.getLanguage().getCode()
                        .equalsIgnoreCase(languageCode.strip()))
                .findFirst()
                .orElseThrow();
    }
}
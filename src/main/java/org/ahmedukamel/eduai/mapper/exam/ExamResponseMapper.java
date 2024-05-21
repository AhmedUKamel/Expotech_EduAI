package org.ahmedukamel.eduai.mapper.exam;

import org.ahmedukamel.eduai.dto.exam.ExamResponse;
import org.ahmedukamel.eduai.model.Exam;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ExamResponseMapper implements Function<Exam, ExamResponse> {
    @Override
    public ExamResponse apply(Exam exam) {
        return new ExamResponse(
                exam.getId(),
                exam.getName(),
                exam.getTerm(),
                exam.getCreatedAt(),
                exam.getUpdatedAt(),
                exam.getStartAt(),
                exam.getEndAt(),
                exam.getUser().getId(),
                exam.getSchool().getId(),
                exam.getSemester().getId(),
                exam.isActive(),
                exam.isNoticePublished(),
                exam.isResultPublished()
        );
    }
}
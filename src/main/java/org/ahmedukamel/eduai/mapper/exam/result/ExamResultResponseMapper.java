package org.ahmedukamel.eduai.mapper.exam.result;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.exam.result.ExamResultResponse;
import org.ahmedukamel.eduai.model.ExamResult;
import org.ahmedukamel.eduai.service.message.MessageSourceService;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class ExamResultResponseMapper implements Function<ExamResult, ExamResultResponse> {
    private final MessageSourceService messageSourceService;

    @Override
    public ExamResultResponse apply(ExamResult examResult) {
        return new ExamResultResponse(
                examResult.getId(),
                examResult.getStudent().getId(),
                examResult.getExam().getId(),
                messageSourceService.getExamResultStatus(examResult.getStatus()),
                examResult.getScoreDate(),
                examResult.getScore()
        );
    }
}
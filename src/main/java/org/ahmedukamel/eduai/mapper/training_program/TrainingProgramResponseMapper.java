package org.ahmedukamel.eduai.mapper.training_program;

import org.ahmedukamel.eduai.dto.training_program.TrainingProgramResponse;
import org.ahmedukamel.eduai.model.Parent;
import org.ahmedukamel.eduai.model.ParentDetail;
import org.ahmedukamel.eduai.model.TrainingProgram;
import org.ahmedukamel.eduai.model.TrainingProgramDetails;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.function.Predicate;
@Component
public class TrainingProgramResponseMapper implements Function<TrainingProgram, TrainingProgramResponse> {
    @Override
    public TrainingProgramResponse apply(TrainingProgram trainingProgram) {
        TrainingProgramDetails trainingProgramDetails = this.getDetails(trainingProgram);
        return new TrainingProgramResponse(
          trainingProgram.getId(),
          trainingProgram.getTitle(),
          trainingProgram.getDescription(),
          trainingProgram.getStartDate(),
          trainingProgram.getEndDate(),
          trainingProgram.getSchool().getId()
        );
    }

    private TrainingProgramDetails getDetails(TrainingProgram trainingProgram) {
        Predicate<TrainingProgramDetails> filter = (i) -> i.getLanguage().getCode()
                .equalsIgnoreCase(LocaleContextHolder.getLocale().getLanguage());

        return trainingProgram.getTrainingProgramDetails()
                .stream()
                .filter(filter)
                .findFirst()
                .orElseThrow();
    }
}

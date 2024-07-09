package org.ahmedukamel.eduai.mapper.training_program;

import org.ahmedukamel.eduai.dto.training_program.TrainingProgramResponse;
import org.ahmedukamel.eduai.model.*;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.util.training_program.TrainingProgramUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.function.Predicate;
@Component
public class TrainingProgramResponseMapper implements Function<TrainingProgram, TrainingProgramResponse> {
    @Override
    public TrainingProgramResponse apply(TrainingProgram trainingProgram) {

        TrainingProgramDetails details_en = TrainingProgramUtils.getTrainingProgramDetails(trainingProgram, Language.ENGLISH);
        TrainingProgramDetails details_ar = TrainingProgramUtils.getTrainingProgramDetails(trainingProgram, Language.ARABIC);
        TrainingProgramDetails details_fr = TrainingProgramUtils.getTrainingProgramDetails(trainingProgram, Language.FRENCH);
        return new TrainingProgramResponse(
          trainingProgram.getId(),
          details_ar.getTitle(),
          details_en.getTitle(),
          details_fr.getTitle(),
          details_ar.getDescription(),
          details_en.getDescription(),
          details_fr.getDescription(),
          details_en.getObjectives(),
          details_ar.getObjectives(),
          details_fr.getObjectives(),
          trainingProgram.getStartDate(),
          trainingProgram.getEndDate(),
          trainingProgram.getTargetAudience(),
          trainingProgram.getSchedule(),
          trainingProgram.getDuration(),
          trainingProgram.getLocation(),
          trainingProgram.getTrainingStatus(),
          trainingProgram.getCost(),
          trainingProgram.isDeleted()
        );
    }
}

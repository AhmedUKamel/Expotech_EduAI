package org.ahmedukamel.eduai.updater.training_program;

import org.ahmedukamel.eduai.dto.training_program.UpdateTrainingProgramRequest;
import org.ahmedukamel.eduai.model.TrainingProgram;
import org.ahmedukamel.eduai.model.TrainingProgramDetails;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.repository.TrainingProgramRepository;
import org.ahmedukamel.eduai.util.training_program.TrainingProgramUtils;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;
@Component
public class TrainingProgramUpdater implements BiFunction<TrainingProgram,UpdateTrainingProgramRequest, TrainingProgram> {
    private final TrainingProgramRepository trainingProgramRepository;

    public TrainingProgramUpdater(TrainingProgramRepository trainingProgramRepository) {
        this.trainingProgramRepository = trainingProgramRepository;
    }

    @Override
    public TrainingProgram apply(TrainingProgram trainingProgram, UpdateTrainingProgramRequest request) {
        trainingProgram.setStartDate(request.startDate());
        trainingProgram.setEndDate(request.endDate());
        trainingProgram.setDeleted(request.deleted());

        TrainingProgramDetails details_en = TrainingProgramUtils.getTrainingProgramDetails(trainingProgram, Language.ENGLISH);
        details_en.setTitle(request.title_en());
        details_en.setDescription(request.description_en());

        TrainingProgramDetails details_ar = TrainingProgramUtils.getTrainingProgramDetails(trainingProgram, Language.ARABIC);
        details_ar.setTitle(request.title_ar());
        details_ar.setDescription(request.description_ar());

        TrainingProgramDetails details_fr = TrainingProgramUtils.getTrainingProgramDetails(trainingProgram, Language.FRENCH);
        details_fr.setTitle(request.title_fr());
        details_fr.setDescription(request.description_fr());

        return trainingProgramRepository.save(trainingProgram);
    }


}

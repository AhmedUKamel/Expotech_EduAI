package org.ahmedukamel.eduai.saver.training_program;

import org.ahmedukamel.eduai.dto.training_program.CreateTrainingProgramRequest;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.model.TrainingProgram;
import org.ahmedukamel.eduai.model.TrainingProgramDetails;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.repository.TrainingProgramRepository;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.function.BiFunction;
@Component
public class TrainingProgramSaver implements BiFunction<CreateTrainingProgramRequest, School, TrainingProgram> {
    private final TrainingProgramRepository trainingProgramRepository;
    public TrainingProgramSaver(TrainingProgramRepository trainingProgramRepository) {
        this.trainingProgramRepository = trainingProgramRepository;
    }


    @Override
    public TrainingProgram apply(CreateTrainingProgramRequest request, School school) {
        TrainingProgram trainingProgram =  new TrainingProgram();
        trainingProgram.setStartDate(request.startDate());
        trainingProgram.setEndDate(request.endDate());
        trainingProgram.setDeleted(request.deleted());
        TrainingProgramDetails trainingProgramDetails_en = new TrainingProgramDetails();
        trainingProgramDetails_en.setTrainingProgram(trainingProgram);
        trainingProgramDetails_en.setLanguage(Language.ENGLISH);
        trainingProgramDetails_en.setDescription(request.title_en().strip());
        trainingProgramDetails_en.setTitle(request.description_en().strip());

        TrainingProgramDetails trainingProgramDetails_ar = new TrainingProgramDetails();
        trainingProgramDetails_ar.setTrainingProgram(trainingProgram);
        trainingProgramDetails_ar.setLanguage(Language.ARABIC);
        trainingProgramDetails_ar.setDescription(request.title_ar().strip());
        trainingProgramDetails_ar.setTitle(request.description_ar().strip());

        TrainingProgramDetails trainingProgramDetails_fr = new TrainingProgramDetails();
        trainingProgramDetails_fr.setTrainingProgram(trainingProgram);
        trainingProgramDetails_fr.setLanguage(Language.FRENCH);
        trainingProgramDetails_fr.setDescription(request.title_fr().strip());
        trainingProgramDetails_fr.setTitle(request.description_fr().strip());

        trainingProgram.setTrainingProgramDetails(Set.of(
                trainingProgramDetails_en,
                trainingProgramDetails_ar,
                trainingProgramDetails_fr
        ));

        return trainingProgramRepository.save(trainingProgram);
    }
}

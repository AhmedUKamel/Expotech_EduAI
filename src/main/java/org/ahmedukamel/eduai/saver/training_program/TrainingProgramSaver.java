package org.ahmedukamel.eduai.saver.training_program;

import org.ahmedukamel.eduai.dto.training_program.CreateTrainingProgramRequest;
import org.ahmedukamel.eduai.model.TrainingProgram;
import org.ahmedukamel.eduai.model.TrainingProgramDetails;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.repository.TrainingProgramRepository;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.function.Function;

@Component
public class TrainingProgramSaver implements Function<CreateTrainingProgramRequest, TrainingProgram> {
    private final TrainingProgramRepository trainingProgramRepository;
    public TrainingProgramSaver(TrainingProgramRepository trainingProgramRepository) {
        this.trainingProgramRepository = trainingProgramRepository;
    }

    @Override
    public TrainingProgram apply(CreateTrainingProgramRequest request) {
        TrainingProgram trainingProgram = TrainingProgram.builder()
                .startDate(request.startDate())
                .endDate(request.endDate())
                .targetAudience(request.targetAudience())
                .schedule(request.schedule())
                .duration(request.duration())
                .location(request.location())
                .trainingStatus(request.trainingStatus())
                .Cost(request.Cost())
                .deleted(request.deleted())
                .build();

        TrainingProgramDetails trainingProgramDetails_en = TrainingProgramDetails.builder()
                .trainingProgram(trainingProgram)
                .title(request.title_en())
                .description(request.description_en())
                .objectives(request.objective_en())
                .language(Language.ENGLISH)
                .build();

        TrainingProgramDetails trainingProgramDetails_ar = TrainingProgramDetails.builder()
                .trainingProgram(trainingProgram)
                .title(request.title_ar())
                .description(request.description_ar())
                .objectives(request.objective_ar())
                .language(Language.ARABIC)
                .build();

        TrainingProgramDetails trainingProgramDetails_fr = TrainingProgramDetails.builder()
                .trainingProgram(trainingProgram)
                .title(request.title_fr())
                .description(request.description_fr())
                .objectives(request.objective_fr())
                .language(Language.FRENCH)
                .build();

        trainingProgram.setTrainingProgramDetails(Set.of(trainingProgramDetails_en, trainingProgramDetails_ar, trainingProgramDetails_fr));

        return trainingProgramRepository.save(trainingProgram);

    }
}

package org.ahmedukamel.eduai.util.training_program;

import org.ahmedukamel.eduai.model.TrainingProgram;
import org.ahmedukamel.eduai.model.TrainingProgramDetails;
import org.ahmedukamel.eduai.model.enumeration.Language;

public class TrainingProgramUtils {

    public static TrainingProgramDetails getTrainingProgramDetails(TrainingProgram trainingProgram, Language language) {
        return getTrainingProgramDetails(trainingProgram, language.getCode());
    }
    public static TrainingProgramDetails getTrainingProgramDetails(TrainingProgram trainingProgram, String languageCode) {
        return trainingProgram.getTrainingProgramDetails()
                .stream()
                .filter(trainingProgramDetails -> trainingProgramDetails.getLanguage().getCode()
                        .equalsIgnoreCase(languageCode.strip()))
                .findFirst()
                .orElseThrow();
    }
}

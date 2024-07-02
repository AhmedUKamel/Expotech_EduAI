//package org.ahmedukamel.eduai.updater.training_program;
//
//import org.ahmedukamel.eduai.dto.training_program.UpdateTrainingProgramRequest;
//import org.ahmedukamel.eduai.model.TrainingProgram;
//import org.ahmedukamel.eduai.repository.SemesterRepository;
//import org.ahmedukamel.eduai.repository.TrainingProgramRepository;
//import org.springframework.stereotype.Component;
//
//import java.util.function.BiFunction;
//@Component
//public class TrainingProgramUpdater implements BiFunction<TrainingProgram,UpdateTrainingProgramRequest, TrainingProgram> {
//    private final SemesterRepository semesterRepository;
//    private final TrainingProgramRepository trainingProgramRepository;
//    public TrainingProgramUpdater(SemesterRepository semesterRepository, TrainingProgramRepository trainingProgramRepository) {
//        this.semesterRepository = semesterRepository;
//        this.trainingProgramRepository = trainingProgramRepository;
//    }
//    @Override
//    public TrainingProgram apply(TrainingProgram trainingProgram, UpdateTrainingProgramRequest request) {
//        trainingProgram.setTitle_ar(request.title_ar());
//        trainingProgram.setTitle_en(request.title_en());
//        trainingProgram.setTitle_fr(request.title_fr());
//        trainingProgram.setDescription_ar(request.description_ar());
//        trainingProgram.setDescription_en(request.description_en());
//        trainingProgram.setDescription_fr(request.description_fr());
//        trainingProgram.setStartDate(request.startDate());
//        trainingProgram.setEndDate(request.endDate());
//        trainingProgram.setSemester(semesterRepository.findById(request.semesterId()).get());
//        trainingProgramRepository.save(trainingProgram);
//        return trainingProgram;
//    }
//}

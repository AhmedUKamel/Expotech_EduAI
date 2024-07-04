package org.ahmedukamel.eduai.service.training_program;

import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.training_program.CreateTrainingProgramRequest;
import org.ahmedukamel.eduai.dto.training_program.TrainingProgramResponse;
import org.ahmedukamel.eduai.mapper.training_program.TrainingProgramResponseMapper;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.model.TrainingProgram;
import org.ahmedukamel.eduai.repository.TrainingProgramRepository;
import org.ahmedukamel.eduai.saver.training_program.TrainingProgramSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.util.context.ContextHolderUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TrainingProgramService implements ITrainingProgramService {
    private final TrainingProgramRepository trainingProgramRepository;
    private final TrainingProgramSaver trainingProgramSaver;
    private final TrainingProgramResponseMapper trainingProgramResponseMapper;
    public TrainingProgramService(TrainingProgramRepository trainingProgramRepository, TrainingProgramSaver trainingProgramSaver, TrainingProgramResponseMapper trainingProgramResponseMapper) {
        this.trainingProgramRepository = trainingProgramRepository;
        this.trainingProgramSaver = trainingProgramSaver;
        this.trainingProgramResponseMapper = trainingProgramResponseMapper;
    }
    @Override
    public Object UpdateTrainingProgram(Long id) {
        return null;
    }

    @Override
    public Object addTrainingProgram(Object object) {

        CreateTrainingProgramRequest request = (CreateTrainingProgramRequest) object;
        School school = ContextHolderUtils.getEmployee().getSchool();

        TrainingProgram trainingProgram = trainingProgramSaver.apply(request, school);
        TrainingProgramResponse response = trainingProgramResponseMapper.apply(trainingProgram);
        String message = "Training program added successfully.";
        return new ApiResponse(true, message, response);
    }

    @Override
    public Object softDeleteTrainingProgram(Long id) {
        School school = ContextHolderUtils.getEmployee().getSchool();

        TrainingProgram trainingProgram = trainingProgramRepository.findById(id).orElseThrow(() -> new RuntimeException("Training program not found."));

        try {
            trainingProgramRepository.delete(trainingProgram);
        } catch (DataIntegrityViolationException exception) {
            throw new RuntimeException("Training program is associated with other records and can't be deleted.", exception);
        }
        String message = "Training program deleted successfully.";

        return new ApiResponse(true, message, "");
    }

    @Override
    public Object getTrainingProgram(Long id) {
        School school = ContextHolderUtils.getEmployee().getSchool();
<<<<<<< HEAD
        TrainingProgram trainingProgram = trainingProgramRepository.findByIdAndSchool_Id(
                id, school.getId()).orElseThrow( () -> new RuntimeException("Training program not found."));
=======
        TrainingProgram trainingProgram = trainingProgramRepository.findById(id).orElseThrow(() -> new RuntimeException("Training program not found."));
>>>>>>> c86963c4dcf390a108d84a3af89d8aaf6f15672f
        TrainingProgramResponse response = trainingProgramResponseMapper.apply(trainingProgram);

        String message = "Training program retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getAllTrainingProgram(int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        School school = ContextHolderUtils.getEmployee().getSchool();

        Page<TrainingProgram> trainingPrograms = trainingProgramRepository.findAll(pageable);
        Page<TrainingProgramResponse> response = trainingPrograms.map(trainingProgramResponseMapper);
        String message = "All training programs retrieved successfully.";

        return new ApiResponse(true, message, response);
    }
}

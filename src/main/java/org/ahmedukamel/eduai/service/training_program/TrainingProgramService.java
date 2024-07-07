package org.ahmedukamel.eduai.service.training_program;

import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.training_program.CreateTrainingProgramRequest;
import org.ahmedukamel.eduai.dto.training_program.TrainingProgramResponse;
import org.ahmedukamel.eduai.dto.training_program.UpdateTrainingProgramRequest;
import org.ahmedukamel.eduai.mapper.training_program.TrainingProgramResponseMapper;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.model.TrainingProgram;
import org.ahmedukamel.eduai.repository.TrainingProgramRepository;
import org.ahmedukamel.eduai.saver.training_program.TrainingProgramSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.updater.training_program.TrainingProgramUpdater;
import org.ahmedukamel.eduai.util.context.ContextHolderUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingProgramService implements ITrainingProgramService {
    private final TrainingProgramRepository trainingProgramRepository;
    private final TrainingProgramSaver trainingProgramSaver;
    private final TrainingProgramResponseMapper trainingProgramResponseMapper;
    private final TrainingProgramUpdater trainingProgramUpdater;
    public TrainingProgramService(TrainingProgramRepository trainingProgramRepository, TrainingProgramSaver trainingProgramSaver, TrainingProgramResponseMapper trainingProgramResponseMapper, TrainingProgramUpdater trainingProgramUpdater) {
        this.trainingProgramRepository = trainingProgramRepository;
        this.trainingProgramSaver = trainingProgramSaver;
        this.trainingProgramResponseMapper = trainingProgramResponseMapper;
        this.trainingProgramUpdater = trainingProgramUpdater;
    }
    @Override
    public Object updateTrainingProgram(Long id,Object object) {
        TrainingProgram trainingProgram = DatabaseService.get(trainingProgramRepository::findById, id, TrainingProgram.class);
        UpdateTrainingProgramRequest request = (UpdateTrainingProgramRequest) object;
        TrainingProgram updatedTrainingProgram = trainingProgramUpdater.apply(trainingProgram, request);
        TrainingProgramResponse response = trainingProgramResponseMapper.apply(updatedTrainingProgram);
        String message = "Training program updated successfully.";
        return new ApiResponse(true, message, response);
    }

    @Override
    public Object addTrainingProgram(Object object) {

        CreateTrainingProgramRequest request = (CreateTrainingProgramRequest) object;
        TrainingProgram trainingProgram = trainingProgramSaver.apply(request);
        TrainingProgramResponse response = trainingProgramResponseMapper.apply(trainingProgram);
        String message = "Training program added successfully.";
        return new ApiResponse(true, message, response);
    }

    @Override
    public Object softDeleteTrainingProgram(Long id) {
        TrainingProgram trainingProgram = trainingProgramRepository.findById(id).orElseThrow(() -> new RuntimeException("Training program not found."));

        try {
            trainingProgram.setDeleted(true);
            trainingProgramRepository.save(trainingProgram);
        } catch (DataIntegrityViolationException exception) {
            throw new RuntimeException("Training program is associated with other records and can't be deleted.", exception);
        }
        String message = "Training program deleted successfully.";

        return new ApiResponse(true, message, "");
    }

    @Override
    public Object getTrainingProgram(Long id) {
        TrainingProgram trainingProgram = trainingProgramRepository.findById(id).orElseThrow(() -> new RuntimeException("Training program not found."));
        if (!trainingProgram.isDeleted()){

            TrainingProgramResponse response = trainingProgramResponseMapper.apply(trainingProgram);
            String message = "Training program retrieved successfully.";

            return new ApiResponse(true, message, response);
        }

        String message = "Training program not found.";
        return new ApiResponse(false, message, "");

    }

    @Override
    public Object getAllTrainingProgram(int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<TrainingProgram> trainingPrograms = trainingProgramRepository.findAll(pageable);
        List<TrainingProgram> filteredList = trainingPrograms
                .stream()
                .filter(trainingProgram -> !trainingProgram.isDeleted())
                .collect(Collectors.toList());
        Page<TrainingProgram> filteredPage = new PageImpl<>(filteredList, pageable, filteredList.size());
        Page<TrainingProgramResponse> response = filteredPage.map(trainingProgramResponseMapper);
        String message = "All training programs retrieved successfully.";

        return new ApiResponse(true, message, response);
    }
}

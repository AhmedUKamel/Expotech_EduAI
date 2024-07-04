package org.ahmedukamel.eduai.service.training_program;

import org.springframework.stereotype.Service;

@Service
public interface ITrainingProgramService {
    Object UpdateTrainingProgram(Long id);

    Object addTrainingProgram(Object object);

    Object softDeleteTrainingProgram(Long id);

    Object getTrainingProgram(Long id);

    Object getAllTrainingProgram(int pageSize, int pageNumber);
}

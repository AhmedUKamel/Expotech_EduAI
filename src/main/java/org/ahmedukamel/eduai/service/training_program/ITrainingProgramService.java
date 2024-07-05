package org.ahmedukamel.eduai.service.training_program;

import org.springframework.stereotype.Service;

@Service
public interface ITrainingProgramService {
    Object updateTrainingProgram(Long id,Object object);

    Object addTrainingProgram(Object object);

    Object softDeleteTrainingProgram(Long id);

    Object getTrainingProgram(Long id);

    Object getAllTrainingProgram(int pageSize, int pageNumber);
}

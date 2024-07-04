package org.ahmedukamel.eduai.service.position;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.position.AllLanguagesPositionResponse;
import org.ahmedukamel.eduai.dto.position.CreatePositionRequest;
import org.ahmedukamel.eduai.dto.position.SingleLanguagePositionResponse;
import org.ahmedukamel.eduai.dto.position.UpdatePositionRequest;
import org.ahmedukamel.eduai.mapper.position.AllLanguagesPositionResponseMapper;
import org.ahmedukamel.eduai.mapper.position.SingleLanguagePositionResponseMapper;
import org.ahmedukamel.eduai.model.Position;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.repository.PositionRepository;
import org.ahmedukamel.eduai.saver.position.CreatePositionRequestSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.updater.position.UpdatePositionRequestUpdater;
import org.ahmedukamel.eduai.util.context.ContextHolderUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PositionManagementService implements IPositionManagementService {
    private final SingleLanguagePositionResponseMapper singleLanguagePositionResponseMapper;
    private final AllLanguagesPositionResponseMapper allLanguagesPositionResponseMapper;
    private final UpdatePositionRequestUpdater updatePositionRequestUpdater;
    private final CreatePositionRequestSaver createPositionRequestSaver;
    private final PositionRepository positionRepository;

    @Override
    public Object createPosition(Object object) {
        CreatePositionRequest request = (CreatePositionRequest) object;

        Position savedPosition = createPositionRequestSaver.apply(request);

        AllLanguagesPositionResponse response = allLanguagesPositionResponseMapper.apply(savedPosition);
        String message = "Position created successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object updatePosition(Integer id, Object object) {
        Position position = DatabaseService.get(positionRepository::findById, id, Position.class);
        UpdatePositionRequest request = (UpdatePositionRequest) object;

        Position updatedPosition = updatePositionRequestUpdater.apply(position, request);

        AllLanguagesPositionResponse response = allLanguagesPositionResponseMapper.apply(updatedPosition);
        String message = "Position updated successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object deletePosition(Integer id) {
        School school = ContextHolderUtils.getEmployee().getSchool();
        Position position = DatabaseService.get(positionRepository::findByIdAndDepartment_School_Id,
                id, school.getId(), Position.class);
        try {
            positionRepository.delete(position);
        } catch (DataIntegrityViolationException exception) {
            throw new RuntimeException("This position is associated with other records and can't be deleted.", exception);
        }

        String message = "Position deleted successfully.";

        return new ApiResponse(true, message, "");
    }

    @Override
    public Object getPosition(Integer id) {
        School school = ContextHolderUtils.getEmployee().getSchool();
        Position position = DatabaseService.get(positionRepository::findByIdAndDepartment_School_Id,
                id, school.getId(), Position.class);

        AllLanguagesPositionResponse response = allLanguagesPositionResponseMapper.apply(position);
        String message = "Position retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getAllPositions(int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        School school = ContextHolderUtils.getEmployee().getSchool();

        Page<Position> positions = positionRepository.findAllByDepartment_School_Id(school.getId(), pageable);

        Page<SingleLanguagePositionResponse> response = positions.map(singleLanguagePositionResponseMapper);
        String message = "All positions retrieved successfully.";

        return new ApiResponse(true, message, response);
    }
}
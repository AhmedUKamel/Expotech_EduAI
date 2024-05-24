package org.ahmedukamel.eduai.service.position;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.position.CreatePositionRequest;
import org.ahmedukamel.eduai.dto.position.PositionResponse;
import org.ahmedukamel.eduai.dto.position.UpdatePositionRequest;
import org.ahmedukamel.eduai.mapper.position.PositionResponseMapper;
import org.ahmedukamel.eduai.model.Department;
import org.ahmedukamel.eduai.model.Position;
import org.ahmedukamel.eduai.repository.DepartmentRepository;
import org.ahmedukamel.eduai.repository.PositionRepository;
import org.ahmedukamel.eduai.saver.position.PositionSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.updater.position.PositionUpdater;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionManagementService implements IPositionManagementService {
    private final PositionResponseMapper positionResponseMapper;
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;
    private final PositionUpdater positionUpdater;
    private final PositionSaver positionSaver;

    @Override
    public Object createPosition(Object object) {
        CreatePositionRequest request = (CreatePositionRequest) object;

        Position savedPosition = positionSaver.apply(request);

        PositionResponse response = positionResponseMapper.apply(savedPosition);
        String message = "Position created successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object updatePosition(Integer id, Object object) {
        Position position = DatabaseService.get(positionRepository::findById, id, Position.class);
        UpdatePositionRequest request = (UpdatePositionRequest) object;

        Position updatedPosition = positionUpdater.apply(position, request);

        PositionResponse response = positionResponseMapper.apply(updatedPosition);
        String message = "Position updated successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object deletePosition(Integer id) {
        Position position = DatabaseService.get(positionRepository::findById, id, Position.class);

        positionRepository.delete(position);

        String message = "Position deleted successfully.";

        return new ApiResponse(true, message, "");
    }

    @Override
    public Object getPosition(Integer id) {
        Position position = DatabaseService.get(positionRepository::findById, id, Position.class);

        PositionResponse response = positionResponseMapper.apply(position);
        String message = "Position retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getAllPositions(long pageSize, long pageNumber) {
        List<Position> positions = positionRepository
                .selectPositionsWithPagination(pageSize, pageSize * (pageNumber - 1));

        List<PositionResponse> response = positions
                .stream()
                .map(positionResponseMapper)
                .toList();
        String message = "Positions retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getPositionsByDepartment(Integer departmentId, long pageSize, long pageNumber) {
        Department department = DatabaseService.get(departmentRepository::findById, departmentId, Department.class);

        List<Position> positions = positionRepository
                .selectPositionsByDepartmentWithPagination(department, pageSize, pageSize * (pageNumber - 1));

        List<PositionResponse> response = positions
                .stream()
                .map(positionResponseMapper)
                .toList();
        String message = "Positions retrieved successfully.";

        return new ApiResponse(true, message, response);
    }
}
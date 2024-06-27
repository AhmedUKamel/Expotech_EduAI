package org.ahmedukamel.eduai.service.room.lab;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.room.lab.CreateLabRequest;
import org.ahmedukamel.eduai.dto.room.lab.LabResponse;
import org.ahmedukamel.eduai.dto.room.lab.UpdateLabRequest;
import org.ahmedukamel.eduai.mapper.room.LabResponseMapper;
import org.ahmedukamel.eduai.model.Lab;
import org.ahmedukamel.eduai.repository.LabRepository;
import org.ahmedukamel.eduai.repository.RoomRepository;
import org.ahmedukamel.eduai.saver.room.LabSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.updater.room.LabUpdater;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LabManagementService implements ILabManagementService {
    private final LabResponseMapper LabResponseMapper;
    private final LabRepository LabRepository;
    private final LabUpdater LabUpdater;
    private final LabSaver LabSaver;
    private final RoomRepository roomRepository;

    @Override
    public Object createLab(Object object) {
        CreateLabRequest request = (CreateLabRequest) object;

        Lab savedLab = LabSaver.apply(request);

        LabResponse response = LabResponseMapper.apply(savedLab);
        String message = "Lab created successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object updateLab(Long id, Object object) {
        Lab Lab = DatabaseService.get(LabRepository::findById, id, Lab.class);
        UpdateLabRequest request = (UpdateLabRequest) object;

        Lab updateLab = LabUpdater.apply(Lab, request);

        LabResponse response = LabResponseMapper.apply(updateLab);
        String message = "Lab updated successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object deleteLab(Long id) {
        Lab Lab = DatabaseService.get(LabRepository::findById, id, Lab.class);

        roomRepository.delete(Lab.getRoom());

        String message = "Lab deleted successfully.";

        return new ApiResponse(true, message, "");
    }

    @Override
    public Object getLab(Long id) {
        Lab Lab = DatabaseService.get(LabRepository::findById, id, Lab.class);

        LabResponse response = LabResponseMapper.apply(Lab);
        String message = "Lab retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getAllLabs(long pageSize, long pageNumber) {
        List<Lab> Labs = LabRepository
                .selectLabsWithPagination(pageSize, pageSize * (pageNumber - 1));

        List<LabResponse> response = Labs
                .stream()
                .map(LabResponseMapper)
                .toList();
        String message = "Labs retrieved successfully.";

        return new ApiResponse(true, message, response);
    }
}
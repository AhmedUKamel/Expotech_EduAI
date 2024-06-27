package org.ahmedukamel.eduai.service.room.office;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.room.office.CreateOfficeRequest;
import org.ahmedukamel.eduai.dto.room.office.OfficeResponse;
import org.ahmedukamel.eduai.dto.room.office.UpdateOfficeRequest;
import org.ahmedukamel.eduai.mapper.room.OfficeResponseMapper;
import org.ahmedukamel.eduai.model.Office;
import org.ahmedukamel.eduai.repository.OfficeRepository;
import org.ahmedukamel.eduai.repository.RoomRepository;
import org.ahmedukamel.eduai.saver.room.OfficeSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.updater.room.OfficeUpdater;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfficeManagementService implements IOfficeManagementService {
    private final OfficeResponseMapper officeResponseMapper;
    private final OfficeRepository officeRepository;
    private final OfficeUpdater officeUpdater;
    private final OfficeSaver officeSaver;
    private final RoomRepository roomRepository;

    @Override
    public Object createOffice(Object object) {
        CreateOfficeRequest request = (CreateOfficeRequest) object;

        Office savedOffice = officeSaver.apply(request);

        OfficeResponse response = officeResponseMapper.apply(savedOffice);
        String message = "Office created successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object updateOffice(Long id, Object object) {
        Office office = DatabaseService.get(officeRepository::findById, id, Office.class);
        UpdateOfficeRequest request = (UpdateOfficeRequest) object;

        Office updateOffice = officeUpdater.apply(office, request);

        OfficeResponse response = officeResponseMapper.apply(updateOffice);
        String message = "office updated successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object deleteOffice(Long id) {
        Office office = DatabaseService.get(officeRepository::findById, id, Office.class);

        roomRepository.delete(office.getRoom());

        String message = "office deleted successfully.";

        return new ApiResponse(true, message, "");
    }

    @Override
    public Object getOffice(Long id) {
        Office office = DatabaseService.get(officeRepository::findById, id, Office.class);

        OfficeResponse response = officeResponseMapper.apply(office);
        String message = "office retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getAllOffices(long pageSize, long pageNumber) {
        List<Office> offices = officeRepository
                .selectOfficesWithPagination(pageSize, pageSize * (pageNumber - 1));

        List<OfficeResponse> response = offices
                .stream()
                .map(officeResponseMapper)
                .toList();
        String message = "offices retrieved successfully.";

        return new ApiResponse(true, message, response);
    }
}
package org.ahmedukamel.eduai.service.bus;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.bus.BusResponse;
import org.ahmedukamel.eduai.dto.bus.CreateBusRequest;
import org.ahmedukamel.eduai.dto.bus.UpdateBusRequest;
import org.ahmedukamel.eduai.mapper.bus.BusResponseMapper;
import org.ahmedukamel.eduai.model.Bus;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.repository.BusRepository;
import org.ahmedukamel.eduai.saver.bus.BusSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.updater.bus.BusUpdater;
import org.ahmedukamel.eduai.util.context.ContextHolderUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusManagementService implements IBusManagementService {
    private final BusResponseMapper busResponseMapper;
    private final BusRepository busRepository;
    private final BusUpdater busUpdater;
    private final BusSaver busSaver;

    @Override
    public Object createBus(Object object) {
        CreateBusRequest request = (CreateBusRequest) object;

        Bus savedBus = busSaver.apply(request);

        BusResponse response = busResponseMapper.apply(savedBus);
        String message = "Bus created successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object updateBus(Long id, Object object) {
        Bus Bus = DatabaseService.get(busRepository::findById, id, Bus.class);
        UpdateBusRequest request = (UpdateBusRequest) object;

        Bus updatedBus = busUpdater.apply(Bus, request);

        BusResponse response = busResponseMapper.apply(updatedBus);
        String message = "Bus updated successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object deleteBus(Long id) {
        Bus bus = DatabaseService.get(busRepository::findById, id, Bus.class);

        bus.setDeleted(true);
        busRepository.save(bus);

        String message = "Bus deleted successfully.";

        return new ApiResponse(true, message, "");
    }

    @Override
    public Object getBus(Long id) {
        Bus Bus = DatabaseService.get(busRepository::findById, id, Bus.class);

        BusResponse response = busResponseMapper.apply(Bus);
        String message = "Bus retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getAllBuses(boolean getActive, int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        School school = ContextHolderUtils.getEmployee().getSchool();

        Page<Bus> buses = busRepository.findAllBySchoolIdAndDeleted(school.getId(), !getActive, pageable);

        Page<BusResponse> response = buses.map(busResponseMapper);
        String message = "Buses retrieved successfully.";

        return new ApiResponse(true, message, response);
    }
}
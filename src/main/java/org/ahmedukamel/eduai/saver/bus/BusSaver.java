package org.ahmedukamel.eduai.saver.bus;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.bus.CreateBusRequest;
import org.ahmedukamel.eduai.model.Bus;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.repository.BusRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.util.context.ContextHolderUtils;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class BusSaver implements Function<CreateBusRequest, Bus> {
    private final BusRepository busRepository;

    @Override
    public Bus apply(CreateBusRequest request) {
        School school = ContextHolderUtils.getEmployee().getSchool();

        DatabaseService.unique(busRepository::existsBySchool_IdAndBusNumberIgnoreCase,
                school.getId(), request.busNumber().strip(), Bus.class);

        Bus bus = Bus
                .builder()
                .school(school)
                .busNumber(request.busNumber().strip().toUpperCase())
                .driverName(request.driverName().strip())
                .driverNumber(request.driverNumber().strip())
                .route(request.route())
                .busCapacity(request.busCapacity())
                .build();

        return busRepository.save(bus);
    }
}
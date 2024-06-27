package org.ahmedukamel.eduai.updater.bus;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.bus.UpdateBusRequest;
import org.ahmedukamel.eduai.model.Bus;
import org.ahmedukamel.eduai.repository.BusRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class BusUpdater implements BiFunction<Bus, UpdateBusRequest, Bus> {
    private final BusRepository busRepository;

    @Override
    public Bus apply(Bus bus, UpdateBusRequest request) {
        String busNumber = request.busNumber().strip();

        if (!bus.getBusNumber().equalsIgnoreCase(busNumber)) {
            DatabaseService.unique(busRepository::existsBySchool_IdAndBusNumberIgnoreCase,
                    bus.getSchool().getId(), busNumber, Bus.class);
        }

        bus.setBusNumber(request.busNumber());
        bus.setDriverName(request.driverName());
        bus.setDriverNumber(request.driverNumber());
        bus.setBusCapacity(request.busCapacity());
        bus.setRoute(request.route());

        return busRepository.save(bus);
    }
}
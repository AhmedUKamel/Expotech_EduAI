package org.ahmedukamel.eduai.updater.section;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.section.UpdateSectionRequest;
import org.ahmedukamel.eduai.model.Section;
import org.ahmedukamel.eduai.model.User;
import org.ahmedukamel.eduai.repository.SectionRepository;
import org.ahmedukamel.eduai.repository.UserRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;

@Component
@RequiredArgsConstructor
public class SectionUpdater implements BiFunction<Section, UpdateSectionRequest, Section> {
    private final SectionRepository sectionRepository;
    private final UserRepository userRepository;

    @Override
    public Section apply(Section section, UpdateSectionRequest request) {
        section.setName(request.name().strip());
        section.setNumber(request.number().strip());
        section.setRoomNumber(request.roomNumber().strip());

        section.setUser(DatabaseService.get(userRepository::findById, request.userId(), User.class));

        return sectionRepository.save(section);
    }
}
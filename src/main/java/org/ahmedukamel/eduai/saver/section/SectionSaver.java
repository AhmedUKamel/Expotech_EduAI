package org.ahmedukamel.eduai.saver.section;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.section.CreateSectionRequest;
import org.ahmedukamel.eduai.model.Class;
import org.ahmedukamel.eduai.model.Section;
import org.ahmedukamel.eduai.model.User;
import org.ahmedukamel.eduai.repository.ClassRepository;
import org.ahmedukamel.eduai.repository.SectionRepository;
import org.ahmedukamel.eduai.repository.UserRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class SectionSaver implements Function<CreateSectionRequest, Section> {
    private final SectionRepository sectionRepository;
    private final ClassRepository classRepository;
    private final UserRepository userRepository;

    @Override
    public Section apply(CreateSectionRequest request) {
        Section section = new Section();

        section.setName(request.name().strip());
        section.setNumber(request.number().strip());
        section.setRoomNumber(request.roomNumber().strip());

        section.setUser(DatabaseService.get(userRepository::findById, request.userId(), User.class));
        section.setTheClass(DatabaseService.get(classRepository::findById, request.classId(), Class.class));

        return sectionRepository.save(section);
    }
}
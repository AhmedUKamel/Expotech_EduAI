package org.ahmedukamel.eduai.updater.section;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.section.UpdateSectionRequest;
import org.ahmedukamel.eduai.model.Section;
import org.ahmedukamel.eduai.model.Student;
import org.ahmedukamel.eduai.repository.SectionRepository;
import org.ahmedukamel.eduai.repository.StudentRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class SectionUpdater implements BiFunction<Section, UpdateSectionRequest, Section> {
    private final SectionRepository sectionRepository;
    private final StudentRepository studentRepository;

    @Override
    public Section apply(Section section, UpdateSectionRequest request) {
        Collection<Student> students = request.studentsId()
                .stream()
                .flatMap(Stream::ofNullable)
                .map(studentRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

        section.setName(request.name().strip());
        section.setNumber(request.number().strip());
        section.setStudents(students);

        return sectionRepository.save(section);
    }
}
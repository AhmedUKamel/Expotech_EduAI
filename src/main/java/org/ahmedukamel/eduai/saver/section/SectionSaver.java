package org.ahmedukamel.eduai.saver.section;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.section.CreateSectionRequest;
import org.ahmedukamel.eduai.model.Classroom;
import org.ahmedukamel.eduai.model.Section;
import org.ahmedukamel.eduai.model.Student;
import org.ahmedukamel.eduai.repository.ClassroomRepository;
import org.ahmedukamel.eduai.repository.SectionRepository;
import org.ahmedukamel.eduai.repository.StudentRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class SectionSaver implements Function<CreateSectionRequest, Section> {
    private final ClassroomRepository classroomRepository;
    private final SectionRepository sectionRepository;
    private final StudentRepository studentRepository;

    @Override
    public Section apply(CreateSectionRequest request) {
        Classroom classroom = DatabaseService.get(classroomRepository::findById, request.classroomId(), Classroom.class);

        Collection<Student> students = request.studentsId()
                .stream()
                .flatMap(Stream::ofNullable)
                .map(studentRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

        Section section = Section
                .builder()
                .classroom(classroom)
                .students(students)
                .name(request.name().strip())
                .number(request.number().strip())
                .build();

        return sectionRepository.save(section);
    }
}
package org.ahmedukamel.eduai.saver.position;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.position.CreatePositionRequest;
import org.ahmedukamel.eduai.model.Department;
import org.ahmedukamel.eduai.model.Position;
import org.ahmedukamel.eduai.model.PositionDetails;
import org.ahmedukamel.eduai.model.enumeration.Language;
import org.ahmedukamel.eduai.repository.DepartmentRepository;
import org.ahmedukamel.eduai.repository.PositionRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class PositionSaver implements Function<CreatePositionRequest, Position> {
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;

    @Override
    public Position apply(CreatePositionRequest request) {
        Department department = DatabaseService.get(departmentRepository::findById, request.departmentId(), Department.class);

        Position position = new Position();

        PositionDetails positionDetails_en = new PositionDetails(
                position, Language.ENGLISH, request.title_en().strip()
        ), positionDetails_ar = new PositionDetails(
                position, Language.ARABIC, request.title_ar().strip()
        ), positionDetails_fr = new PositionDetails(
                position, Language.FRENCH, request.title_fr().strip()
        );

        position.setDepartment(department);
        position.setDetails(List.of(
                positionDetails_en, positionDetails_ar, positionDetails_fr
        ));

        return positionRepository.save(position);
    }
}
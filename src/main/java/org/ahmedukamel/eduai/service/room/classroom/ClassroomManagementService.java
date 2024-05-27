package org.ahmedukamel.eduai.service.room.classroom;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.room.classroom.ClassroomResponse;
import org.ahmedukamel.eduai.dto.room.classroom.CreateClassroomRequest;
import org.ahmedukamel.eduai.dto.room.classroom.UpdateClassroomRequest;
import org.ahmedukamel.eduai.mapper.room.ClassroomResponseMapper;
import org.ahmedukamel.eduai.model.Classroom;
import org.ahmedukamel.eduai.repository.ClassroomRepository;
import org.ahmedukamel.eduai.repository.RoomRepository;
import org.ahmedukamel.eduai.saver.room.ClassroomSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.updater.room.ClassroomUpdater;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassroomManagementService implements IClassroomManagementService {
    private final ClassroomResponseMapper classroomResponseMapper;
    private final ClassroomRepository classroomRepository;
    private final ClassroomUpdater classroomUpdater;
    private final ClassroomSaver classroomSaver;
    private final RoomRepository roomRepository;

    @Override
    public Object createClassroom(Object object) {
        CreateClassroomRequest request = (CreateClassroomRequest) object;

        Classroom savedClassroom = classroomSaver.apply(request);

        ClassroomResponse response = classroomResponseMapper.apply(savedClassroom);
        String message = "Classroom created successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object updateClassroom(Long id, Object object) {
        Classroom classroom = DatabaseService.get(classroomRepository::findById, id, Classroom.class);
        UpdateClassroomRequest request = (UpdateClassroomRequest) object;

        Classroom updateClassroom = classroomUpdater.apply(classroom, request);

        ClassroomResponse response = classroomResponseMapper.apply(updateClassroom);
        String message = "Classroom updated successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object deleteClassroom(Long id) {
        Classroom classroom = DatabaseService.get(classroomRepository::findById, id, Classroom.class);

        roomRepository.delete(classroom.getRoom());

        String message = "Classroom deleted successfully.";

        return new ApiResponse(true, message, "");
    }

    @Override
    public Object getClassroom(Long id) {
        Classroom classroom = DatabaseService.get(classroomRepository::findById, id, Classroom.class);

        ClassroomResponse response = classroomResponseMapper.apply(classroom);
        String message = "Classroom retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getAllClassrooms(long pageSize, long pageNumber) {
        List<Classroom> classrooms = classroomRepository
                .selectClassroomsWithPagination(pageSize, pageSize * (pageNumber - 1));

        List<ClassroomResponse> response = classrooms
                .stream()
                .map(classroomResponseMapper)
                .toList();
        String message = "Classrooms retrieved successfully.";

        return new ApiResponse(true, message, response);
    }
}
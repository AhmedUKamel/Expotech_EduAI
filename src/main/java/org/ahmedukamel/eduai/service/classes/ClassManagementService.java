package org.ahmedukamel.eduai.service.classes;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.classes.ClassResponse;
import org.ahmedukamel.eduai.dto.classes.CreateClassRequest;
import org.ahmedukamel.eduai.dto.classes.UpdateClassRequest;
import org.ahmedukamel.eduai.mapper.classes.ClassResponseMapper;
import org.ahmedukamel.eduai.model.Class;
import org.ahmedukamel.eduai.repository.ClassRepository;
import org.ahmedukamel.eduai.saver.classes.ClassSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.updater.classes.ClassUpdater;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassManagementService implements IClassManagementService {
    private final ClassResponseMapper classResponseMapper;
    private final ClassRepository classRepository;
    private final ClassUpdater classUpdater;
    private final ClassSaver classSaver;

    @Override
    public Object createClass(Object object) {
        CreateClassRequest request = (CreateClassRequest) object;

        Class savedClass = classSaver.apply(request);

        ClassResponse response = classResponseMapper.apply(savedClass);
        String message = "Class created successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object updateClass(Integer id, Object object) {
        Class theClass = DatabaseService.get(classRepository::findById, id, Class.class);
        UpdateClassRequest request = (UpdateClassRequest) object;

        Class updatedClass = classUpdater.apply(theClass, request);

        ClassResponse response = classResponseMapper.apply(updatedClass);
        String message = "Class updated successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object deleteClass(Integer id) {
        Class theClass = DatabaseService.get(classRepository::findById, id, Class.class);

        classRepository.delete(theClass);

        String message = "Class deleted successfully.";

        return new ApiResponse(true, message, "");
    }

    @Override
    public Object getClass(Integer id) {
        Class theClass = DatabaseService.get(classRepository::findById, id, Class.class);

        ClassResponse response = classResponseMapper.apply(theClass);
        String message = "Class retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getAllClasses(long pageSize, long pageNumber) {
        List<Class> classes = classRepository
                .selectClassesWithPagination(pageSize, pageSize * (pageNumber - 1));

        List<ClassResponse> response = classes
                .stream()
                .map(classResponseMapper)
                .toList();
        String message = "Classes retrieved successfully.";

        return new ApiResponse(true, message, response);
    }
}
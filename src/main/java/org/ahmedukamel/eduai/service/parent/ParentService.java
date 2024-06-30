package org.ahmedukamel.eduai.service.parent;

import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.parent.CreateParentRequest;
import org.ahmedukamel.eduai.dto.parent.ParentResponse;
import org.ahmedukamel.eduai.dto.parent.UpdateParentRequest;
import org.ahmedukamel.eduai.mapper.parent.ParentResponseMapper;
import org.ahmedukamel.eduai.model.Parent;
import org.ahmedukamel.eduai.repository.ParentRepository;
import org.ahmedukamel.eduai.saver.parent.CreateParentSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.updater.parent.ParentUpdater;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParentService implements IParentService{
    private final ParentResponseMapper parentResponseMapper;
    private final ParentRepository parentRepository;
    private final CreateParentSaver createParentSaver;
    private final ParentUpdater parentUpdater;


    public ParentService(ParentResponseMapper parentResponseMapper, ParentRepository parentRepository, CreateParentSaver createParentSaver, ParentUpdater parentUpdater) {
        this.parentResponseMapper = parentResponseMapper;
        this.parentRepository = parentRepository;
        this.createParentSaver = createParentSaver;
        this.parentUpdater = parentUpdater;
    }

    @Override
    public Object createParent(Object object) {
        CreateParentRequest request = (CreateParentRequest) object;
        Parent parent = createParentSaver.apply(request);
        ParentResponse response = parentResponseMapper.apply(parent);
        String message = "Parent created successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object updateParent(Long id, Object object) {
        Parent parent = DatabaseService.get(parentRepository::findById,id,Parent.class);
        UpdateParentRequest request = (UpdateParentRequest) object;

        Parent updatedParent = parentUpdater.apply(parent, request);

        ParentResponse response = parentResponseMapper.apply(updatedParent);
        String message = "Parent updated successfully.";
        return null;
    }

    @Override
    public Object deleteParent(Long id) {
        Parent parent = DatabaseService.get(parentRepository::findById, id, Parent.class);

        parentRepository.delete(parent);
        String message = "Parent deleted successfully.";

        return new ApiResponse(true, message, "");
    }

    @Override
    public Object getParent(Long id) {
        Parent parent = DatabaseService.get(parentRepository::findById, id, Parent.class);
        ParentResponse response = parentResponseMapper.apply(parent);

        String message = "Parent retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getAllParent(long pageSize, long pageNumber) {
        List<Parent> parents = parentRepository.selectParentWithPagination(pageSize, pageSize * (pageNumber - 1));
        List<ParentResponse > response = parents
                .stream()
                .map(parentResponseMapper)
                .toList();

        String message = "Parents retrieved successfully.";

        return new ApiResponse(true, message, response);
    }
}


package org.ahmedukamel.eduai.service.parent;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.parent.AddParentRequest;
import org.ahmedukamel.eduai.dto.parent.UpdateParentRequest;
import org.ahmedukamel.eduai.dto.profile.ParentProfileResponse;
import org.ahmedukamel.eduai.mapper.profile.ParentProfileResponseMapper;
import org.ahmedukamel.eduai.model.Parent;
import org.ahmedukamel.eduai.model.School;
import org.ahmedukamel.eduai.repository.ParentRepository;
import org.ahmedukamel.eduai.saver.parent.IParentRegistrationRequestSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.updater.parent.ParentRequestUpdater;
import org.ahmedukamel.eduai.util.context.ContextHolderUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParentManagementService implements IParentManagementService {
    private final IParentRegistrationRequestSaver iParentRegistrationRequestSaver;
    private final ParentRequestUpdater parentRequestUpdater;
    private final ParentProfileResponseMapper parentProfileResponseMapper;
    private final ParentRepository parentRepository;

    @Override
    public Object addParent(Object object) {
        AddParentRequest request = (AddParentRequest) object;
        School school = ContextHolderUtils.getEmployee().getSchool();

        Parent parent = iParentRegistrationRequestSaver.apply(request, school);

        ParentProfileResponse response = parentProfileResponseMapper.apply(parent);
        String message = "Parent added successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object updateParent(Object object) {
        Parent parent = ContextHolderUtils.getParent();
        UpdateParentRequest request = (UpdateParentRequest) object;

        Parent updatedParent = parentRequestUpdater.apply(parent, request);

        ParentProfileResponse response = parentProfileResponseMapper.apply(updatedParent);
        String message = "Parent updated successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object setParentAccountLock(Long id, boolean accountLocked) {
        School school = ContextHolderUtils.getEmployee().getSchool();
        Parent parent = DatabaseService.get(parentRepository::findByIdAndSchool_Id,
                id, school.getId(), Parent.class);

        parent.setAccountNonLocked(!accountLocked);
        parentRepository.save(parent);

        String message = "Parent account lock set successfully.";

        return new ApiResponse(true, message, "");
    }

    @Override
    public Object getParent(Long id) {
        School school = ContextHolderUtils.getEmployee().getSchool();

        Parent parent = DatabaseService.get(parentRepository::findByIdAndSchool_Id,
                id, school.getId(), Parent.class);

        ParentProfileResponse response = parentProfileResponseMapper.apply(parent);
        String message = "Parent retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getAllParents(int pageSize, int pageNumber) {
        School school = ContextHolderUtils.getEmployee().getSchool();
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Parent> parents = parentRepository.findAllBySchool_Id(school.getId(), pageable);

        Page<ParentProfileResponse> response = parents.map(parentProfileResponseMapper);
        String message = "All parents retrieved successfully.";

        return new ApiResponse(true, message, response);
    }
}
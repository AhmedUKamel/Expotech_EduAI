package org.ahmedukamel.eduai.service.user_management;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.user_management.AdminParentResponse;
import org.ahmedukamel.eduai.mapper.user_management.AdminParentResponseMapper;
import org.ahmedukamel.eduai.model.Parent;
import org.ahmedukamel.eduai.repository.ParentRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminParentService implements IAdminParentService {
    private final AdminParentResponseMapper adminParentResponseMapper;
    private final ParentRepository parentRepository;

    @Override
    public Object getAllParents(int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by("id").ascending());

        Page<Parent> parents = parentRepository.findAll(pageable);

        Page<AdminParentResponse> response = parents.map(adminParentResponseMapper);
        String message = "All parents retrieved successfully";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getParent(Long parentId) {
        Parent parent = DatabaseService.get(parentRepository::findById, parentId, Parent.class);

        AdminParentResponse response = adminParentResponseMapper.apply(parent);
        String message = "Parent retrieved successfully";

        return new ApiResponse(true, message, response);
    }
}

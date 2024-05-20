package org.ahmedukamel.eduai.service.section;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.section.CreateSectionRequest;
import org.ahmedukamel.eduai.dto.section.SectionResponse;
import org.ahmedukamel.eduai.dto.section.UpdateSectionRequest;
import org.ahmedukamel.eduai.mapper.section.SectionResponseMapper;
import org.ahmedukamel.eduai.model.Section;
import org.ahmedukamel.eduai.repository.SectionRepository;
import org.ahmedukamel.eduai.saver.section.SectionSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.updater.section.SectionUpdater;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SectionManagementService implements ISectionManagementService {
    private final SectionResponseMapper sectionResponseMapper;
    private final SectionRepository sectionRepository;
    private final SectionUpdater sectionUpdater;
    private final SectionSaver sectionSaver;

    @Override
    public Object createSection(Object object) {
        CreateSectionRequest request = (CreateSectionRequest) object;

        Section savedSection = sectionSaver.apply(request);

        SectionResponse response = sectionResponseMapper.apply(savedSection);
        String message = "Section created successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object updateSection(Integer id, Object object) {
        Section section = DatabaseService.get(sectionRepository::findById, id, Section.class);
        UpdateSectionRequest request = (UpdateSectionRequest) object;

        Section updatedSection = sectionUpdater.apply(section, request);

        SectionResponse response = sectionResponseMapper.apply(updatedSection);
        String message = "Section updated successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object deleteSection(Integer id) {
        Section section = DatabaseService.get(sectionRepository::findById, id, Section.class);

        sectionRepository.delete(section);

        String message = "Section deleted successfully.";

        return new ApiResponse(true, message, "");
    }

    @Override
    public Object getSection(Integer id) {
        Section section = DatabaseService.get(sectionRepository::findById, id, Section.class);

        SectionResponse response = sectionResponseMapper.apply(section);
        String message = "Section retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getAllSections(long pageSize, long pageNumber) {
        List<Section> sections = sectionRepository
                .selectSectionsWithPagination(pageSize, pageSize * (pageNumber - 1));

        List<SectionResponse> response = sections
                .stream()
                .map(sectionResponseMapper)
                .toList();
        String message = "Sections retrieved successfully.";

        return new ApiResponse(true, message, response);
    }
}
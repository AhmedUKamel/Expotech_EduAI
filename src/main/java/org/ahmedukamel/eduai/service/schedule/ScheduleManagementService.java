package org.ahmedukamel.eduai.service.schedule;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.schedule.*;
import org.ahmedukamel.eduai.mapper.schedule.ScheduleItemClassResponseMapper;
import org.ahmedukamel.eduai.mapper.schedule.ScheduleItemResponseMapper;
import org.ahmedukamel.eduai.mapper.schedule.ScheduleItemTeacherResponseMapper;
import org.ahmedukamel.eduai.model.ScheduleItem;
import org.ahmedukamel.eduai.repository.ScheduleItemRepository;
import org.ahmedukamel.eduai.saver.schedule.ScheduleItemSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.updater.schedule.ScheduleItemUpdater;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleManagementService implements IScheduleManagementService {

    private final ScheduleItemResponseMapper scheduleItemResponseMapper;
    private final ScheduleItemTeacherResponseMapper scheduleItemTeacherResponseMapper;
    private final ScheduleItemClassResponseMapper scheduleItemClassResponseMapper;
    private final ScheduleItemRepository scheduleItemRepository;
    private final ScheduleItemSaver saver;
    private final ScheduleItemUpdater updater;

    @Override
    public Object createScheduleItem(Object object) {
        CreateScheduleItemRequest request = (CreateScheduleItemRequest) object;

        ScheduleItem savedScheduleItem = saver.apply(request);

        ScheduleItemResponse response = scheduleItemResponseMapper.apply(savedScheduleItem);

        String message = "Schedule Item created successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object updateScheduleItem(Long scheduleItemId, Object object) {
        ScheduleItem scheduleItem = DatabaseService.get(scheduleItemRepository::findById, scheduleItemId, ScheduleItem.class);
        UpdateScheduleItemRequest request = (UpdateScheduleItemRequest) object;

        ScheduleItem savedScheduleItem = updater.apply(scheduleItem, request);

        ScheduleItemResponse response = scheduleItemResponseMapper.apply(savedScheduleItem);

        String message = "Schedule Item updated successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getScheduleItem(Long scheduleItemId) {

        ScheduleItem scheduleItem = DatabaseService.get(scheduleItemRepository::findById, scheduleItemId, ScheduleItem.class);

        ScheduleItemResponse response = scheduleItemResponseMapper.apply(scheduleItem);

        String message = "Schedule Item retrieved successfully.";

        return new ApiResponse(true, message, response);
    }

    @Override
    public Object deleteScheduleItem(Long scheduleItemId) {
        ScheduleItem scheduleItem = DatabaseService.get(scheduleItemRepository::findById, scheduleItemId, ScheduleItem.class);

        scheduleItem.setDeleted(true);

        scheduleItemRepository.save(scheduleItem);

        String message = "Schedule Item deleted successfully.";

        return new ApiResponse(true, message, "");
    }

    @Override
    public Object getScheduleForTeacher(Long teacherId, boolean getActive, int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<ScheduleItem> scheduleItemPage = scheduleItemRepository
                .findAllByTeacherIdAndDeleted(teacherId, !getActive, pageable);
        Page<ScheduleItemResponseForTeacher> scheduleItemResponseForTeacherPage =
                scheduleItemPage.map(scheduleItemTeacherResponseMapper);

        String status = getActive? "Active":"Deleted";
        String message = status + " Schedule Items for teacher retrieved successfully.";

        return new ApiResponse(true, message, scheduleItemResponseForTeacherPage);
    }

    @Override
    public Object getScheduleForClassroom(Long classroomId, boolean getActive, int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<ScheduleItem> scheduleItemPage = scheduleItemRepository
                .findAllByClassroomIdAndDeleted(classroomId, !getActive, pageable);
        Page<ScheduleItemResponseForClass> scheduleItemResponseForClassPage =
                scheduleItemPage.map(scheduleItemClassResponseMapper);

        String status = getActive? "Active":"Deleted";
        String message = status + "Schedule Items for class retrieved successfully.";

        return new ApiResponse(true, message, scheduleItemResponseForClassPage);
    }
}

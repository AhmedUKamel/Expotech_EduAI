package org.ahmedukamel.eduai.updater.news;

import jakarta.persistence.EntityNotFoundException;
import org.ahmedukamel.eduai.dto.news.UpdateNewsRequest;
import org.ahmedukamel.eduai.model.Employee;
import org.ahmedukamel.eduai.model.News;
import org.ahmedukamel.eduai.model.Teacher;
import org.ahmedukamel.eduai.repository.EmployeeRepository;
import org.ahmedukamel.eduai.repository.NewsRepository;
import org.ahmedukamel.eduai.repository.TeacherRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.BiFunction;
@Component
public class NewsUpdater implements BiFunction<News, UpdateNewsRequest,News>{

    private final NewsRepository newsRepository;
    private final TeacherRepository teacherRepository;
    private final EmployeeRepository employeeRepository;
    public NewsUpdater(NewsRepository newsRepository,TeacherRepository teacherRepository, EmployeeRepository employeeRepository) {
        this.newsRepository = newsRepository;
        this.teacherRepository = teacherRepository;
        this.employeeRepository = employeeRepository;
    }
    @Override
    public News apply(News news, UpdateNewsRequest request) {
        String content = request.content().strip();
//
//        Teacher teacher = null;
//        if (request.teacherId() != null) {
//            Optional<Teacher> teacherOptional = teacherRepository.findById(request.teacherId());
//            teacher = teacherOptional.orElse(null);
//        }
//
//        Employee employee = null;
//        if (request.employeeId() != null) {
//            Optional<Employee> employeeOptional = employeeRepository.findById(request.employeeId());
//            employee = employeeOptional.orElse(null);
//        }
        LocalDateTime currentDateTime = LocalDateTime.now();
        news.setContent(content);
        news.setLocalDateTime(currentDateTime);

        return newsRepository.save(news);
    }
}
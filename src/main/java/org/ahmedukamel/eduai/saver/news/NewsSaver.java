package org.ahmedukamel.eduai.saver.news;

import org.ahmedukamel.eduai.dto.news.CreateNewsRequest;
import org.ahmedukamel.eduai.model.Employee;
import org.ahmedukamel.eduai.model.News;
import org.ahmedukamel.eduai.model.Teacher;
import org.ahmedukamel.eduai.repository.EmployeeRepository;
import org.ahmedukamel.eduai.repository.NewsRepository;
import org.ahmedukamel.eduai.repository.TeacherRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Function;
@Component
public class NewsSaver implements Function<CreateNewsRequest, News> {
    private final TeacherRepository teacherRepository;
    private final EmployeeRepository employeeRepository;
    private final NewsRepository repository;
    public NewsSaver(TeacherRepository teacherRepository, EmployeeRepository employeeRepository, NewsRepository repository) {
        this.teacherRepository = teacherRepository;
        this.employeeRepository = employeeRepository;
        this.repository = repository;
    }
    @Override
    public News apply(CreateNewsRequest createNewsRequest) {
        String content = createNewsRequest.content().strip();

        Teacher teacher = null;
        if (createNewsRequest.teacherId() != null) {
            Optional<Teacher> teacherOptional = teacherRepository.findById(createNewsRequest.teacherId());
            teacher = teacherOptional.orElse(null);
        }

        Employee employee = null;
        if (createNewsRequest.employeeId() != null) {
            Optional<Employee> employeeOptional = employeeRepository.findById(createNewsRequest.employeeId());
            employee = employeeOptional.orElse(null);
        }
        LocalDateTime currentDateTime = LocalDateTime.now();

        News news = News.builder()
                .content(content)
                .teacher(teacher)
                .employee(employee)
                .localDateTime(currentDateTime)
                .build();

        return repository.save(news);
    }
}

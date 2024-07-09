package org.ahmedukamel.eduai.mapper.news;

import org.ahmedukamel.eduai.dto.news.NewsResponse;
import org.ahmedukamel.eduai.model.Employee;
import org.ahmedukamel.eduai.model.News;
import org.ahmedukamel.eduai.model.Teacher;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class NewsResponseMapper implements Function<News, NewsResponse> {
    @Override
    public NewsResponse apply(News news) {
        return new NewsResponse(
                news.getId(),
                news.getContent(),
                news.getLocalDateTime(),
                news.getTeacher().getId(),
                news.getEmployee().getId()
        );

    }

    public NewsResponse apply(News news, Teacher teacher, Employee employee) {
        Long teacherId = teacher != null ? news.getTeacher().getId() : null;
        Long employeeId = employee != null ? news.getEmployee().getId() : null;

        // Mapping logic, ensuring to handle null or empty teacher and employee
        return new NewsResponse(news.getId(), news.getContent(), news.getLocalDateTime(), teacherId, employeeId);
    }
}

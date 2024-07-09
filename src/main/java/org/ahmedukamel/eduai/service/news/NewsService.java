package org.ahmedukamel.eduai.service.news;

import org.ahmedukamel.eduai.dto.api.ApiResponse;
import org.ahmedukamel.eduai.dto.news.CreateNewsRequest;
import org.ahmedukamel.eduai.dto.news.NewsResponse;
import org.ahmedukamel.eduai.dto.news.UpdateNewsRequest;
import org.ahmedukamel.eduai.mapper.news.NewsResponseMapper;
import org.ahmedukamel.eduai.model.Employee;
import org.ahmedukamel.eduai.model.News;
import org.ahmedukamel.eduai.model.Teacher;
import org.ahmedukamel.eduai.repository.NewsRepository;
import org.ahmedukamel.eduai.saver.news.NewsSaver;
import org.ahmedukamel.eduai.service.db.DatabaseService;
import org.ahmedukamel.eduai.updater.news.NewsUpdater;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsService implements INewsService {
    private final NewsSaver newsSaver;
    private final NewsUpdater newsUpdater;
    private final NewsResponseMapper newsResponseMapper;
    private final NewsRepository newsRepository;

    public NewsService(NewsSaver newsSaver, NewsUpdater newsUpdater, NewsResponseMapper newsResponseMapper, NewsRepository newsRepository) {
        this.newsSaver = newsSaver;
        this.newsUpdater = newsUpdater;
        this.newsResponseMapper = newsResponseMapper;
        this.newsRepository = newsRepository;
    }

    @Override
    public Object addNews(Object object) {
        CreateNewsRequest request = (CreateNewsRequest) object;
        News news = newsSaver.apply(request);
        Teacher teacher = news.getTeacher() != null ? news.getTeacher() : new Teacher(); // or return null based on your logic
        Employee employee = news.getEmployee() != null ? news.getEmployee() : new Employee(); // or return null based on your logic
        NewsResponse response = newsResponseMapper.apply(news,teacher,employee);
        String message = "News created successfully.";
        return new ApiResponse(true, message, response);
    }

    @Override
    public Object updateNews(Integer id, Object object) {
        News news = DatabaseService.get(newsRepository::findById, id, News.class);
        UpdateNewsRequest request = (UpdateNewsRequest) object;
        News updatedNews = newsUpdater.apply(news, request);
        NewsResponse response = newsResponseMapper.apply(updatedNews);
        String message = "News updated successfully.";
        return new ApiResponse(true, message, response);
    }

    @Override
    public Object deleteNews(Integer id) {
        News news = DatabaseService.get(newsRepository::findById, id, News.class);
        try {
            news.setDeleted(true);
            newsRepository.save(news);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("News cannot be deleted.");
        }
        String message = "News deleted successfully.";
        return new ApiResponse(true, message, "");
    }

    @Override
    public Object getNews(Integer id) {
        News news =  DatabaseService.get(newsRepository::findById, id, News.class);
        Teacher teacher = news.getTeacher() != null ? news.getTeacher() : new Teacher(); // or return null based on your logic
        Employee employee = news.getEmployee() != null ? news.getEmployee() : new Employee(); // or return null based on your logic
        NewsResponse response = newsResponseMapper.apply(news,teacher,employee);
        String message = "News retrieved successfully.";
        return new ApiResponse(true, message, response);
    }

    @Override
    public Object getAllNews(Integer pageSize, Integer pageNumber) {
//        Pageable pageable = PageRequest.of(pageNumber,pageSize);
//        Page<News> news = newsRepository.findAll(pageable);
//        List<News> filteredList = news.
//                stream()
//                .filter(news1 -> !news1.isDeleted())
//                .collect(Collectors.toList());
//        Page<News> filteredPage = new PageImpl<>(filteredList,pageable,filteredList.size());
//        List<NewsResponse> responses = filteredPage.map(newsResponseMapper).getContent();
//        String message = "News retrieved successfully.";
//        return new ApiResponse(true, message, responses);

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<News> news = newsRepository.findAll(pageable);

        // Filter out deleted news
        List<News> filteredList = news.stream()
                .filter(news1 -> !news1.isDeleted())
                .collect(Collectors.toList());
        
        List<News> filteredList2 = new ArrayList<>();
        for (News newsItem : filteredList) {
            Teacher teacher = newsItem.getTeacher() != null ? newsItem.getTeacher() : new Teacher(); // or return null based on your logic
            Employee employee = newsItem.getEmployee() != null ? newsItem.getEmployee() : new Employee(); // or return null based on your logic
            newsItem.setTeacher(teacher);
            newsItem.setEmployee(employee);
            filteredList2.add(newsItem);
        }
        Page<News> filteredPage = new PageImpl<>(filteredList2, pageable, filteredList.size());

        List<NewsResponse> responses = filteredPage.map(newsResponseMapper).getContent();

        String message = "News retrieved successfully.";
        return new ApiResponse(true, message, responses);
    }
}

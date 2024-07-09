package org.ahmedukamel.eduai.updater.updater;

import org.ahmedukamel.eduai.dto.news.UpdateNewsRequest;
import org.ahmedukamel.eduai.model.News;
import org.ahmedukamel.eduai.model.User;
import org.ahmedukamel.eduai.repository.NewsRepository;
import org.ahmedukamel.eduai.repository.UserRepository;
import org.ahmedukamel.eduai.service.db.DatabaseService;

import java.util.function.BiFunction;

public class NewsUpdater implements BiFunction<News, UpdateNewsRequest,News>{

    private final NewsRepository newsRepository;
    private final UserRepository userRepository;

    public NewsUpdater(NewsRepository newsRepository, UserRepository userRepository) {
        this.newsRepository = newsRepository;
        this.userRepository = userRepository;
    }
    @Override
    public News apply(News news, UpdateNewsRequest request) {
        news.setContent(request.content());
        news.setUser(DatabaseService.get(userRepository::findById, request.userId(), User.class));
        return newsRepository.save(news);
    }
}
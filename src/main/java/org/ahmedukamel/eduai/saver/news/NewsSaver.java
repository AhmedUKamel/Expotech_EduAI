package org.ahmedukamel.eduai.saver.news;

import org.ahmedukamel.eduai.dto.news.CreateNewsRequest;
import org.ahmedukamel.eduai.model.News;
import org.ahmedukamel.eduai.repository.NewsRepository;
import org.ahmedukamel.eduai.repository.UserRepository;

import java.util.function.Function;

public class NewsSaver implements Function<CreateNewsRequest, News> {
    private final UserRepository userRepository;
    private final NewsRepository repository;
    public NewsSaver(UserRepository userRepository, NewsRepository repository) {
        this.userRepository = userRepository;
        this.repository = repository;
    }
    @Override
    public News apply(CreateNewsRequest createNewsRequest) {
        News news = News
                .builder()
                .content(createNewsRequest.content().strip())
                .user(userRepository.findById(createNewsRequest.userId()).orElseThrow(null))
                .build();
        return repository.save(news);
    }
}

package org.ahmedukamel.eduai.mapper.news;

import org.ahmedukamel.eduai.dto.news.NewsResponse;
import org.ahmedukamel.eduai.model.News;

import java.util.function.Function;

public class NewsResponseMapper implements Function<News, NewsResponse> {
    @Override
    public NewsResponse apply(News news) {
        return new NewsResponse(
                news.getId(),
                news.getContent(),
                news.getLocalDateTime(),
                news.getUser().getId()
        );
    }
}

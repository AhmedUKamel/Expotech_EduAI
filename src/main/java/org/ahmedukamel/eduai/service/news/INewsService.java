package org.ahmedukamel.eduai.service.news;

public interface INewsService {

    Object addNews(Object object);

    Object updateNews(Long id, Object object);

    Object deleteNews(Long id);

    Object getNews(Long id);

    Object getAllNews(long pageSize, long pageNumber);
}

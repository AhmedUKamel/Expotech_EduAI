package org.ahmedukamel.eduai.service.news;

public interface INewsService {

    Object addNews(Object object);

    Object updateNews(Integer id, Object object);

    Object deleteNews(Integer id);

    Object getNews(Integer id);

    Object getAllNews(Integer pageSize, Integer pageNumber);
}

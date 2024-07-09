package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News,Integer> {
}

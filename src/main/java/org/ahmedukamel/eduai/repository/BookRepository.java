package org.ahmedukamel.eduai.repository;

import java.util.List;

import org.ahmedukamel.eduai.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;



public interface BookRepository extends JpaRepository<Book,Integer> {

//    public List<Book> findAllById(List<Integer> bookIds);
}
package org.ahmedukamel.eduai.service.Library;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.Library.BookDTO;
import org.ahmedukamel.eduai.model.Book;
import org.ahmedukamel.eduai.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

//    public List<Book> getAllBooks(){
//        return bookRepository.findAll();
//    }
@PersistenceContext
private EntityManager entityManager;

//    public List<Book> findAllById(List<Integer> bookIds) {
//        String query = "SELECT * FROM books WHERE id IN (:ids)";
//        Query nativeQuery = entityManager.createNativeQuery(query, Book.class);
//        nativeQuery.setParameter("ids", bookIds);
//        return nativeQuery.getResultList();
//    }
public List<Book> findAllById(List<Integer> bookIds) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Book> query = cb.createQuery(Book.class);
    Root<Book> root = query.from(Book.class);
    query.where(root.get("id").in(bookIds));
    return entityManager.createQuery(query).getResultList();
}

    public List<Book> getAllBooksByIds(List<Integer> bookIds ){
        return bookRepository.findAllById(bookIds);
    }

    public Book getBookById(Integer id){
        return bookRepository.findById(id).orElse(null);
    }
    public Book createBook(BookDTO bookDTO){
        Book newBook = new Book();
        newBook.setName(bookDTO.getName());

        newBook.setQuantity(bookDTO.getQuantity());
        return bookRepository.save(newBook);
    }
    public Book replaceBook (Integer id,BookDTO bookDTO){
        Book newBook = new Book();

        newBook.setId(id);
        newBook.setName(bookDTO.getName());

        newBook.setQuantity(bookDTO.getQuantity());
        return bookRepository.save(newBook);
    }

    public Book updateBook(Integer id,BookDTO bookDTO){
        Book oldBook = bookRepository.findById(id).orElse(null);
        if (bookDTO.getName() != null) {
            oldBook.setName(bookDTO.getName());
        }

        if (bookDTO.getQuantity() != null) {
            oldBook.setQuantity(bookDTO.getQuantity());
        }

        return bookRepository.save(oldBook);
    }

    public void deleteBookById(Integer id){
        bookRepository.deleteById(id);
    }
}
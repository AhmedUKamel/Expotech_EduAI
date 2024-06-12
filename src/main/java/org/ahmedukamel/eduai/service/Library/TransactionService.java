package org.ahmedukamel.eduai.service.Library;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.ahmedukamel.eduai.dto.Library.BookRecordDTO;
import org.ahmedukamel.eduai.dto.Library.TransactionDTO;
import org.ahmedukamel.eduai.model.Book;
import org.ahmedukamel.eduai.model.TransactionBook;
import org.ahmedukamel.eduai.model.TransactionHistory;
import org.ahmedukamel.eduai.model.UserDetail;
import org.ahmedukamel.eduai.repository.TransactionRepository;
import org.ahmedukamel.eduai.service.impl.UserDetailsServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserDetailsServiceImpl userService;
    private final BookService bookService;

    @PersistenceContext
    private EntityManager entityManager;
    public List<TransactionHistory> findByUserId(Integer id) {
        Query query = entityManager.createNativeQuery("SELECT * FROM transaction_history WHERE user_id =?", TransactionHistory.class);
        query.setParameter(1, id);
        return query.getResultList();
    }
    // get all transcations
    public List<TransactionHistory> getAllTransactions(){
        return transactionRepository.findAll();
    }
    // get transaction by id
    public TransactionHistory getTransactionById(Integer id){
        return transactionRepository.findById(id).orElse(null);
    }
     //get transaction by userId
//    public List<TransactionHistory> getTransactionByUserId(Integer id){
//        return transactionRepository.findByUserId(id);
//    }
    // delete transction by id
    public void deleteTransactionById(Integer id){
        transactionRepository.deleteById(id);
    }

    // create transaction
    public  TransactionHistory createTransaction(TransactionDTO transactionDTO){
        UserDetails user = userService.getUserById(transactionDTO.getUserId());
        List<TransactionBook> transactionBooksList = new ArrayList<>();
        TransactionHistory transaction = new TransactionHistory();     //  [bookObj,bookObj]
        List<BookRecordDTO> requiredBooks = transactionDTO.getBooks(); // [{bookId:1,quantity:1},{bookId:2,quantity:1}]
        List<Integer> bookIds = requiredBooks.stream().map(bookObj -> bookObj.getBookId()).toList(); // [1,2]
        List<Book> books = bookService.getAllBooksByIds(bookIds); // books [{id:1,name:c++,price:20,quantity:10},{id:1,name:c++,price:20,quantity:10}]
        Double totalPrice = 0.0;
        // update quantity in db
        for(Book book : books){
            for(BookRecordDTO bookObj : requiredBooks){
                if (book.getId() == bookObj.getBookId()) {
                    // update quantity in db
                    book.setQuantity(book.getQuantity() - bookObj.getQuantity());
                    // calculate total price
//                    totalPrice += book.getPrice() * bookObj.getQuantity();
                    // save transaction book
                    TransactionBook tb = new TransactionBook();
                    tb.setBook(book);
                    tb.setQuantity(bookObj.getQuantity());
                    tb.setName(book.getName());
//                    tb.setPrice(book.getPrice());
                    tb.setTransaction(transaction);
                    transactionBooksList.add(tb);
                }
            }
        }

        // set transaction
        transaction.setTransactionBooks(transactionBooksList);
//        transaction.setTotalPrice(totalPrice);
        transaction.setUser((UserDetail) user);
        transaction.setIssuedData(new Date());
        // save Transcation
        return transactionRepository.save(transaction);
    }

}


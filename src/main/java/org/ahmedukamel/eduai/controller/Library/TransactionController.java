package org.ahmedukamel.eduai.controller.Library;

import lombok.RequiredArgsConstructor;
import org.ahmedukamel.eduai.dto.Library.TransactionDTO;
import org.ahmedukamel.eduai.model.TransactionHistory;
import org.ahmedukamel.eduai.service.Library.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/library/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<TransactionHistory>> getAll(){
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionHistory> getOne(@PathVariable Integer id){
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }
//    @GetMapping("/user/{id}")
//    public ResponseEntity<List<TransactionHistory>> getUser(@PathVariable Integer id){
//        return ResponseEntity.ok(transactionService.getTransactionByUserId(id));
//    }

    @PostMapping
    public ResponseEntity<TransactionHistory> createOne(@RequestBody TransactionDTO transactionDTO ){
        return ResponseEntity.ok(transactionService.createTransaction(transactionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOne(@PathVariable Integer id){
        transactionService.deleteTransactionById(id);
        return ResponseEntity.noContent().build();
    }

}

package org.ahmedukamel.eduai.repository;

import java.util.List;

import org.ahmedukamel.eduai.model.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;



public interface TransactionRepository extends JpaRepository<TransactionHistory,Integer> {
//    public List<TransactionHistory> findByUserId(Integer id);

}
package org.ahmedukamel.eduai.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class TransactionBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private TransactionHistory transaction;

    @ManyToOne(cascade = CascadeType.ALL)

    private Book book;

    private String name;
    private Integer quantity;

}
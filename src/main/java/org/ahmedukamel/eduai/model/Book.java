package org.ahmedukamel.eduai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Check;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

//    @Check(constraints = "price >= 0")
//    private Double price;

    @Check(constraints = "quantity >= 0")
    private Integer quantity;

    @OneToMany(mappedBy = "book" , cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TransactionBook> transactionBooks;

    @Transient
    private boolean available;
    public boolean getAvailable(){
        return quantity > 0;
    }
}

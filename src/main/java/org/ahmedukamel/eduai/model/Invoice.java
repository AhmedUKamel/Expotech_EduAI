package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "INVOICES")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime creationDate;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateDate;

    @Temporal(value = TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate dueDate;

    @Column(nullable = false)
    private User billedTo;

    @Column(nullable = false)
    private double paidAmount;

    @Column(nullable = false)
    private double discountAmount;

    @Column(nullable = false)
    private double taxAmount;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Collection<InvoiceDetail> invoiceDetails = new ArrayList<>();

    private boolean deleted = false;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<InvoiceItem> invoiceItems = new ArrayList<>();

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private School school;
}

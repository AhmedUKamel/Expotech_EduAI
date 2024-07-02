package org.ahmedukamel.eduai.model;

import jakarta.persistence.*;
import lombok.*;
import org.ahmedukamel.eduai.model.enumeration.Language;

import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "INVOICE_ITEM_DETAILS")
@IdClass(value = InvoiceItemDetail.InvoiceItemDetailsId.class)
public class InvoiceItemDetail {
    @Id
    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private InvoiceItem invoiceItem;

    @Id
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private Language language;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InvoiceItemDetailsId implements Serializable {
        private InvoiceItem invoiceItem;
        private Language language;
    }
}
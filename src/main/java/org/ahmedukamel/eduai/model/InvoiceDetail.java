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
@Table(name = "INVOICE_DETAILS")
@IdClass(value = InvoiceDetail.InvoiceDetailsId.class)
public class InvoiceDetail {
    @Id
    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private Invoice invoice;

    @Id
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private Language language;

    @Column(nullable = false)
    private String taxDescription;

    @Column(nullable = false)
    private String discountDescription;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InvoiceDetailsId implements Serializable {
        private Invoice invoice;
        private Language language;
    }
}
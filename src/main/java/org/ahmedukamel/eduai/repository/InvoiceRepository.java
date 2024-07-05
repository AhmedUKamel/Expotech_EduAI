package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    Page<Invoice> findAllBySchoolIdAndDeletedOrderByUpdateDate(Integer schoolId, boolean deleted, Pageable pageable);
}
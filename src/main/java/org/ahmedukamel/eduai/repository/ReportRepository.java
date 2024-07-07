package org.ahmedukamel.eduai.repository;

import org.ahmedukamel.eduai.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Integer> {
}
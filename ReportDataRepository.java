package com.vidasalud.ms_vidasalud_report.repository;

import com.vidasalud.ms_vidasalud_report.entity.ReportData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportDataRepository extends JpaRepository<ReportData, Long> {
    long countByEventType(String eventType);
}
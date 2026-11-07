package com.vidasalud.ms_vidasalud_report.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "report_data")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventId;
    private String eventType;
    private String details;
    private LocalDateTime timestamp;
}
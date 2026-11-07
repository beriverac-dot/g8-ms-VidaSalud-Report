package com.vidasalud.ms_vidasalud_report.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportEnvelope {
    private String type;
    private String eventId;
    private LocalDateTime timestamp;
    private String traceId;
    private String correlationId;
    private Object payload;
}
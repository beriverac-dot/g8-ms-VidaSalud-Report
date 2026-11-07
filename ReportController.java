package com.vidasalud.ms_vidasalud_report.controller;

import com.vidasalud.ms_vidasalud_report.repository.ReportDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportDataRepository repository;

    @GetMapping("/kpis")
    public ResponseEntity<Map<String, Object>> getKpis() {
        Map<String, Object> kpis = new HashMap<>();
        
        // Ejemplo de un KPI básico usando el repositorio
        long totalAtencionesConfirmadas = repository.countByEventType("CONFIRMADA");
        
        kpis.put("atencionesConfirmadas", totalAtencionesConfirmadas);
        kpis.put("estado", "OK");
        
        return ResponseEntity.ok(kpis);
    }
}
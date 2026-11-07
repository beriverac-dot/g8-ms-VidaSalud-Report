package com.vidasalud.ms_vidasalud_report.consumer;

import com.rabbitmq.client.Channel;
import com.vidasalud.ms_vidasalud_report.dto.ReportEnvelope;
import com.vidasalud.ms_vidasalud_report.entity.ReportData;
import com.vidasalud.ms_vidasalud_report.repository.ReportDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ReportRabbitConsumer {

    private final ReportDataRepository repository;

    @RabbitListener(queues = "${messaging.queue.report}")
    public void processReportEvent(ReportEnvelope envelope, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        try {
            System.out.println("Evento recibido para reportería: " + envelope.getEventId());

            ReportData data = ReportData.builder()
                    .eventId(envelope.getEventId())
                    .eventType(envelope.getType())
                    .details(envelope.getPayload() != null ? envelope.getPayload().toString() : "Sin datos")
                    .timestamp(envelope.getTimestamp() != null ? envelope.getTimestamp() : LocalDateTime.now())
                    .build();

            repository.save(data);

            // ACK manual
            channel.basicAck(tag, false);

        } catch (Exception e) {
            System.err.println("Error procesando evento de reportería: " + e.getMessage());
            // NACK manual
            channel.basicNack(tag, false, false);
        }
    }
}
package com.mggcode.gestion_bd_elecciones.BD;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EscuchadorBD {


    @KafkaListener(topics = "${spring.kafka.template.default-topic}", groupId = "${municipales.kafka.consumer.group-id}")
    public void listenMunicipales(ConsumerRecord<String, String> record) {
        String payload = record.value();
        System.out.println("Parece que hay un cambio en Municipales");
        // manejar el cambio de datos
    }

    @KafkaListener(topics = "${spring.kafka.template.default-topic}", groupId = "${autonomicas.kafka.consumer.group-id}")
    public void listenAutonomicas(ConsumerRecord<String, String> record) {
        String payload = record.value();
        System.out.println("Parece que hay un cambio en Autonómicas");
        // manejar el cambio de datos
    }
}

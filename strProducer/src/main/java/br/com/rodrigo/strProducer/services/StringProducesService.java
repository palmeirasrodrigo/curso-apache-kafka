package br.com.rodrigo.strProducer.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Log4j2
@Service
@RequiredArgsConstructor
public class StringProducesService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send("str-topic", message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Sent message with success=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "] + " +
                        "] with partition=[" + result.getRecordMetadata().partition() + "] + ");
            } else {
                log.info("Error send message=[" +
                        message + "] due to : " + ex.getMessage());
            }
        });
    }
}

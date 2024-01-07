package br.com.rodrigo.payment.service.impl;

import br.com.rodrigo.payment.model.Payment;
import br.com.rodrigo.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Log4j2
@Service
public class PaymentServiceImpl implements PaymentService {

    private final KafkaTemplate<String, Serializable> kafkaTemplate;

    @SneakyThrows
    @Override
    public void sendPayment(Payment payment) {
        log.info("Recebi o pagamento {}", payment);
        Thread.sleep(1000);

        log.info("Enviando pagamento... ");
        CompletableFuture<SendResult<String, Serializable>> future = kafkaTemplate.send("payment-topic", payment);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Sent message with success=[" + payment +
                        "] with offset=[" + result.getRecordMetadata().offset() + "] + " +
                        "] with partition=[" + result.getRecordMetadata().partition() + "] + ");
            } else {
                log.info("Error send message=[" +
                        payment + "] due to : " + ex.getMessage());
            }
        });
    }
}

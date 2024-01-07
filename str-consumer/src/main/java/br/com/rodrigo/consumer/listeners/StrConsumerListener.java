package br.com.rodrigo.consumer.listeners;

import br.com.rodrigo.consumer.custom.StrConsumerCustomListener;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class StrConsumerListener {

    @SneakyThrows
    @StrConsumerCustomListener(groupId = "group-1")
    public void create(String message) {
        log.info("CREATE ::: message {}", message);
        throw new IllegalArgumentException("Exception:::");
    }

    @StrConsumerCustomListener(groupId = "group-1")
    public void log(String message) {
        log.info("LOG ::: message {}", message);
    }

    @StrConsumerCustomListener(groupId = "group-2")
    public void history(String message) {
        log.info("HISTORY ::: message {}", message);
    }

    @KafkaListener(groupId = "group-3", topics = "str-topic",containerFactory = "validMessageContainerFactory")
    public void interceptor(String message) {
        log.info("INTERCEPTOR ::: message {}", message);
    }
}

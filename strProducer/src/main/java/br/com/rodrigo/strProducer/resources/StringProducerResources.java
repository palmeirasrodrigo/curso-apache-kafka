package br.com.rodrigo.strProducer.resources;

import br.com.rodrigo.strProducer.services.StringProducesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/producer")
public class StringProducerResources {

    private final StringProducesService service;

    @PostMapping
    public ResponseEntity<?> sendMessage(@RequestBody String message) {
        service.sendMessage(message);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

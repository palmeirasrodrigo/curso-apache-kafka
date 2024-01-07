package br.com.rodrigo.payment.resource.impl;

import br.com.rodrigo.payment.model.Payment;
import br.com.rodrigo.payment.resource.PaymentResource;
import br.com.rodrigo.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/payments")
public class PaymentResourceImpl implements PaymentResource {

    private final PaymentService service;

    @Override
    public ResponseEntity<Payment> payment(Payment payment) {
        service.sendPayment(payment);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

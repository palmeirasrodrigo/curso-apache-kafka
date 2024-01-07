package br.com.rodrigo.payment.service;

import br.com.rodrigo.payment.model.Payment;

public interface PaymentService {

    void sendPayment(Payment payment);
}

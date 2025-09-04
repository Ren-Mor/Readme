package Capstone.capstone_project.services;

import Capstone.capstone_project.entities.Ordine;
import Capstone.capstone_project.entities.Payment;
import Capstone.capstone_project.enums.PaymentStatus;
import Capstone.capstone_project.exceptions.NotFoundException;
import Capstone.capstone_project.payloads.NewPaymentDTO;
import Capstone.capstone_project.repositories.OrderRepository;
import Capstone.capstone_project.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private OrderRepository orderRepository;

    public Payment createPayment(NewPaymentDTO payload) {
        Ordine ordine = orderRepository.findById(payload.ordineId())
                .orElseThrow(() -> new NotFoundException(payload.ordineId().intValue()));

        Payment payment = new Payment(
                payload.metodo(),
                payload.importo(),
                PaymentStatus.PENDING,
                ordine
        );
        return paymentRepository.save(payment);
    }

    public Payment getPaymentByOrder(Long ordineId) {
        Ordine ordine = orderRepository.findById(ordineId)
                .orElseThrow(() -> new NotFoundException(ordineId.intValue()));
        return paymentRepository.findByOrdine(ordine)
                .orElseThrow(() -> new NotFoundException(ordineId.intValue()));
    }

    public Payment updatePaymentStatus(Long paymentId, PaymentStatus status) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new NotFoundException(paymentId.intValue()));
        payment.setStatoPagamento(status);
        return paymentRepository.save(payment);
    }
}
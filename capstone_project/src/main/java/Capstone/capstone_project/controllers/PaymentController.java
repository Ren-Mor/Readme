package Capstone.capstone_project.controllers;

import Capstone.capstone_project.entities.Payment;
import Capstone.capstone_project.enums.PaymentStatus;
import Capstone.capstone_project.payloads.NewPaymentDTO;
import Capstone.capstone_project.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // Creazione pagamento per un ordine
    @PostMapping
    public Payment createPayment(@RequestBody @Validated NewPaymentDTO payload) {
        return paymentService.createPayment(payload);
    }

    // Recupera pagamento associato a un ordine
    @GetMapping("/order/{ordineId}")
    public Payment getPaymentByOrder(@PathVariable Long ordineId) {
        return paymentService.getPaymentByOrder(ordineId);
    }

    // Aggiorna stato pagamento (solo ADMIN)
    @PutMapping("/{paymentId}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public Payment updatePaymentStatus(@PathVariable Long paymentId, @RequestParam PaymentStatus status) {
        return paymentService.updatePaymentStatus(paymentId, status);
    }
}
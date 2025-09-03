package Capstone.capstone_project.payloads;

import Capstone.capstone_project.enums.PaymentStatus;

public record PaymentDTO(
        Long id,
        String metodo,
        Double importo,
        PaymentStatus statoPagamento,
        Long ordineId
) {}
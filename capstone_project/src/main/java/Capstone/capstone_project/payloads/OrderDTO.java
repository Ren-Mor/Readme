package Capstone.capstone_project.payloads;

import Capstone.capstone_project.enums.OrderStatus;
import java.time.LocalDateTime;
import java.util.List;

public record OrderDTO(
        Long id,
        String numeroOrdine,
        LocalDateTime dataOrdine,
        Double totale,
        OrderStatus statoOrdine,
        Long utenteId,
        List<OrderItemDTO> items
) {}
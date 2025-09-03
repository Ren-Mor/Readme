package Capstone.capstone_project.payloads;

import java.util.List;

public record CartDTO(
        Long id,
        Long utenteId,
        List<CartItemDTO> items,
        Double totale
) {}

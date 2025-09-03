package Capstone.capstone_project.payloads;

public record OrderItemDTO(
        Long id,
        Long prodottoId,
        String nomeProdotto,
        int quantita,
        Double prezzoUnitario
) {}
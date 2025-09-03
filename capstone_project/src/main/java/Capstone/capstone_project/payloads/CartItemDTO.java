package Capstone.capstone_project.payloads;

public record CartItemDTO(
        Long id,
        Long prodottoId,
        String nomeProdotto,
        int quantita,
        Double prezzoUnitario
) {}
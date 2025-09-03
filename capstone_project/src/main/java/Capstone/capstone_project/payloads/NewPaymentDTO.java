package Capstone.capstone_project.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NewPaymentDTO(
        @NotBlank String metodo,
        @NotNull Double importo,
        @NotNull Long ordineId
) {}
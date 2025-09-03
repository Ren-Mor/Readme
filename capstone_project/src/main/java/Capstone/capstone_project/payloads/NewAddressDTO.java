package Capstone.capstone_project.payloads;

import jakarta.validation.constraints.NotBlank;

public record NewAddressDTO(
        @NotBlank String via,
        @NotBlank String citta,
        @NotBlank String provincia,
        @NotBlank String cap
) {}
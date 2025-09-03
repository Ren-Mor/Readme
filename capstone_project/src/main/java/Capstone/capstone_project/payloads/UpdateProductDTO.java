package Capstone.capstone_project.payloads;

import Capstone.capstone_project.enums.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateProductDTO(
        @NotBlank String nome,
        @NotBlank String descrizione,
        @NotNull Double prezzo,
        @NotBlank String immagine,
        @NotNull Category categoria
) {}

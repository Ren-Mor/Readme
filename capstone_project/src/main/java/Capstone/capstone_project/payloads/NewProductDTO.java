package Capstone.capstone_project.payloads;

import Capstone.capstone_project.enums.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NewProductDTO(
         String nome,
         String descrizione,
         Double prezzo,
         Category categoria
) {}
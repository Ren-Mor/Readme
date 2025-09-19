package Capstone.capstone_project.payloads;

import Capstone.capstone_project.enums.Category;

public record NewProductDTO(
         String nome,
         String descrizione,
         Double prezzo,
         Category categoria
) {}
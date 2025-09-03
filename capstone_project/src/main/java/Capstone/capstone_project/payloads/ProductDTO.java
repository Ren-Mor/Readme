package Capstone.capstone_project.payloads;

import Capstone.capstone_project.enums.Category;

public record ProductDTO(
        Long id,
        String nome,
        String descrizione,
        Double prezzo,
        String immagine,
        Category categoria
) {}
